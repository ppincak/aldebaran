package com.aldebaran.order.api.services;

import com.aldebaran.data.domain.Price;
import com.aldebaran.order.core.CustomerOrderErrorEvents;
import com.aldebaran.order.core.assemblers.CustomerOrderAssembler;
import com.aldebaran.order.core.descriptors.CustomerOrderSearchDescriptors;
import com.aldebaran.order.core.descriptors.CustomerSearchDescriptors;
import com.aldebaran.order.core.entities.*;
import com.aldebaran.order.core.model.CustomerOrderProductsMap;
import com.aldebaran.order.core.model.CustomerOrderRequest;
import com.aldebaran.order.core.model.CustomerOrderResponse;
import com.aldebaran.order.core.model.OrderProduct;
import com.aldebaran.order.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.order.core.repositories.CustomerOrderProductRepository;
import com.aldebaran.order.core.repositories.CustomerOrderRepository;
import com.aldebaran.order.core.repositories.CustomerRepository;
import com.aldebaran.order.core.repositories.SearchCriteriaSpecification;
import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchCriterion;
import com.aldebaran.rest.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class CustomerOrderServiceImpl
        extends AbstractApiService<CustomerOrderRepository, CustomerOrder> implements CustomerOrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderAssembler customerOrderAssembler;

    @Autowired
    private CustomerOrderSearchDescriptors orderSearchDescriptors;

    @Autowired
    private CustomerSearchDescriptors searchDescriptors;

    @Autowired
    private CustomerOrderProductRepository orderProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository repository) {
        super(repository);
    }

    @Override
    public PaginationResponse<CustomerOrderResponse> getCustomerOrders(Long customerId,
                                                                       SearchRequest searchRequest,
                                                                       PaginationRequest paginationRequest) {
        PageRequest pageRequest =
                assemblePageRequest(paginationRequest, orderSearchDescriptors.getOrderDescriptors());

        Set<SearchCriterion> criteria =
                searchRequest.toSearchCriteria(orderSearchDescriptors.getSearchDescriptors());

        Page<CustomerOrder> page;

        if(criteria.isEmpty()) {
            page = repository.getByCustomerId(customerId, pageRequest);
        } else {
            page = repository.findAll(SearchCriteriaSpecification.buildWithAnd(criteria), pageRequest);
        }

        return PaginationResponse
                .data(customerOrderAssembler.toCustomerOrderResponse(page.getContent(),
                                                                     getOrderToProducts(page.getContent())))
                .totalElements(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public CustomerOrderResponse createCustomerOrder(Long customerId,
                                                     CustomerOrderRequest customerOrderRequest) {

        return createCustomerOrder(customerRepository.getOne(customerId),
                                  null,
                                   customerOrderRequest);
    }

    private CustomerOrderResponse createCustomerOrder(Customer customer,
                                                      CustomerOrder customerOrder,
                                                      CustomerOrderRequest customerOrderRequest) {

        Map<Long, Integer> productQuantities  = getQuantities(customerOrderRequest);

        List<Product> products =
                productService.getProducts(productQuantities.keySet());

        if(products.isEmpty()) {
            throw new ApplicationException(CustomerOrderErrorEvents.EMPTY_CUSTOMER_ORDER);
        }

        if(customerOrder == null) {
            customerOrder = new CustomerOrder();
        }
        customerOrder.setCustomer(customer);
        customerOrder.setOrderStatus(OrderStatus.RECEIVED);

        BigDecimal preTaxSum = BigDecimal.ZERO;
        BigDecimal afterTaxSum = BigDecimal.ZERO;

        List<CustomerOrderProduct> orderProducts = new ArrayList<>();
        for(Product product: products) {
            Integer productQuantity = productQuantities.get(product.getId());
            BigDecimal dProductQuantity = new BigDecimal(productQuantity);

            CustomerOrderProduct orderProduct = new CustomerOrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setCustomerOrder(customerOrder);
            orderProduct.setPrice(product.getPrice());
            orderProduct.setQuantity(productQuantity);
            orderProducts.add(orderProduct);

            preTaxSum = preTaxSum
                    .add(product.getPrice().getPreTax().multiply(dProductQuantity));

            afterTaxSum = afterTaxSum
                    .add(product.getPrice().getAfterTax().multiply(dProductQuantity));
        }
        customerOrder.setPriceSum(new Price(preTaxSum, afterTaxSum));
        customerOrder.setOrderProducts(orderProducts);
        repository.save(customerOrder);

        return customerOrderAssembler
                .toCustomerOrderResponse(customerOrder, null);
    }

    @Override
    public CustomerOrderResponse updateCustomerOrder(Long customerOrderId,
                                                     CustomerOrderUpdateRequest customerOrderRequest) {

        if(customerOrderRequest.getUpdateMode() == null) {
            throw new ApplicationException(GeneralErrorEvents.INTERNAL_SERVER_ERROR);
        }

        CustomerOrder customerOrder  = getCustomerOrder(customerOrderId);
        switch (customerOrderRequest.getUpdateMode()) {
            case RESET:
                orderProductRepository.deleteByOrderId(customerOrderId);

                return createCustomerOrder(customerOrder.getCustomer(),
                        customerOrder,
                        customerOrderRequest);
            case APPEND:
                return appendCustomerOrder(customerOrder, customerOrderRequest);
        }
        return null;
    }

    private Map<Long, Integer> getQuantities(CustomerOrderRequest orderRequest) {
        Map<Long, Integer> productQuantities  = new HashMap<>();
        for(OrderProduct orderProduct: orderRequest.getProducts()) {
            Integer quantity = productQuantities.get(orderProduct.getProductId());
            if(quantity == null) {
                quantity = orderProduct.getQuantity();
            } else {
                quantity+= orderProduct.getQuantity();
            }
            productQuantities.put(orderProduct.getProductId(),
                    quantity);
        }
        return productQuantities;
    }

    private Map<Long, List<CustomerOrderProductsMap>> getOrderToProducts(Collection<CustomerOrder> customerOrders) {
        Set<Long> orderIds = customerOrders
                                .stream()
                                .map(CustomerOrder::getId)
                                .collect((Collectors.toCollection(HashSet::new)));

        List<Map<String, Object>> orderProducts = repository.getByCustomerOrderIds(orderIds);

        Map<Long, List<CustomerOrderProductsMap>> orderToProductsMap = new HashMap<>();
        for(Map<String, Object> orderProduct: orderProducts) {
            Long orderId = (Long) orderProduct.get("orderId");
            List<CustomerOrderProductsMap> products =
                    orderToProductsMap
                            .computeIfAbsent(orderId, k -> new ArrayList<>());

            products.add(new CustomerOrderProductsMap(orderProduct));
        }

        return orderToProductsMap;
    }

    private CustomerOrderResponse appendCustomerOrder(CustomerOrder customerOrder,
                                                      CustomerOrderUpdateRequest customerOrderRequest) {

        Map<Long, Integer> productQuantities  = getQuantities(customerOrderRequest);

        List<Product> products =
                productService.getProducts(productQuantities.keySet());

        Map<Long, BigDecimal> productPrices = new HashMap<>();
        for(Product product: products) {
            productPrices.put(product.getId(), product.getPrice().getPreTax());
        }

        List<CustomerOrderProduct> customerOrderProducts = customerOrder.getOrderProducts();

        Set<Long> ignoredProducts = new HashSet<>();
        for(CustomerOrderProduct orderProduct: customerOrderProducts) {
            Long productId = orderProduct.getProductId();
            BigDecimal newPrice = productPrices.get(productId);
            if(newPrice != null &&
                    newPrice.equals(orderProduct.getPrice().getPreTax()) == false) {
                continue;
            }

            int newQuantity = orderProduct.getQuantity() + productQuantities.get(productId);
            orderProduct.setQuantity(newQuantity);
            ignoredProducts.add(productId);
        }

        for(Product product: products) {
            if(ignoredProducts.contains(product.getId())) {
                continue;
            }
            CustomerOrderProduct orderProduct = new CustomerOrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setCustomerOrder(customerOrder);
            orderProduct.setPrice(product.getPrice());
            orderProduct.setQuantity(productQuantities.get(product.getId()));
            customerOrderProducts.add(orderProduct);
        }

        BigDecimal preTaxSum = BigDecimal.ZERO;
        BigDecimal afterTaxSum = BigDecimal.ZERO;

        for(CustomerOrderProduct orderProduct: customerOrderProducts) {
            Integer productQuantity = productQuantities.get(orderProduct.getProduct().getId());
            BigDecimal dProductQuantity = new BigDecimal(productQuantity);

            preTaxSum = preTaxSum
                    .add(orderProduct.getPrice().getPreTax().multiply(dProductQuantity));

            afterTaxSum = afterTaxSum
                    .add(orderProduct.getPrice().getAfterTax().multiply(dProductQuantity));
        }
        customerOrder.setPriceSum(new Price(preTaxSum, afterTaxSum));
        repository.save(customerOrder);

        return customerOrderAssembler.toCustomerOrderResponse(customerOrder);
    }

    @Override
    public void deleteCustomerOder(Long customerOrderId) {
        repository.delete(getCustomerOrder(customerOrderId));
    }

    private CustomerOrder getCustomerOrder(Long customerOrderId) {
        CustomerOrder customerOrder  = repository.findOne(customerOrderId);
        if(customerOrder == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        return customerOrder;
    }
}