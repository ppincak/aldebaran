package com.aldebaran.api.services;

import com.aldebaran.data.domain.Price;
import com.aldebaran.omanager.core.CustomerOrderErrorCodes;
import com.aldebaran.omanager.core.OrderStatus;
import com.aldebaran.omanager.core.Utils;
import com.aldebaran.omanager.core.assemblers.CustomerAssembler;
import com.aldebaran.omanager.core.descriptors.CustomerSearchDescriptors;
import com.aldebaran.omanager.core.entities.Customer;
import com.aldebaran.omanager.core.entities.CustomerOrder;
import com.aldebaran.omanager.core.entities.CustomerOrderProduct;
import com.aldebaran.omanager.core.entities.Product;
import com.aldebaran.omanager.core.model.*;
import com.aldebaran.omanager.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.omanager.core.model.update.CustomerUpdateRequest;
import com.aldebaran.omanager.core.repositories.CustomerOrderProductRepository;
import com.aldebaran.omanager.core.repositories.CustomerOrderRepository;
import com.aldebaran.omanager.core.repositories.CustomerRepository;
import com.aldebaran.omanager.core.repositories.SearchCriteriaSpecification;
import com.aldebaran.rest.error.GeneralErrorCodes;
import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchCriterion;
import com.aldebaran.rest.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.*;


@Service
@Transactional
public class CustomerServiceImpl extends AbstractApiService<CustomerRepository, Customer> implements CustomerService {

    @Autowired
    private CustomerAssembler customerAssembler;

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private CustomerOrderProductRepository orderProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        checkCustomerEmail(null, customerRequest.getEmail());
        Customer customer = customerAssembler.toEntity(customerRequest);
        repository.save(customer);
        return customerAssembler.toResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        return customerAssembler.toResponse(getById(customerId));
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = getById(customerId);
        CustomerRequest request = customerAssembler.merge(customer, customerUpdateRequest);
        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);
        if(violations.isEmpty() == false) {
            throw new ConstraintViolationException(violations);
        }
        checkCustomerEmail(customerId, request.getEmail());

        customerAssembler.merge(customer, request);
        repository.save(customer);
        return customerAssembler.toResponse(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Long ordersCount = repository.ordersCount(customerId);
        if(ordersCount > 0) {
            throw new ApplicationException(CustomerOrderErrorCodes.CUSTOMER_HAS_ORDERS);
        }
        repository.delete(getById(customerId));
    }

    @Override
    public PaginationResponse<CustomerResponse> getCustomers(SearchRequest searchRequest,
                                                             PaginationRequest paginationRequest) {

        PageRequest pageRequest =
                assemblePageRequest(paginationRequest, CustomerSearchDescriptors.getOrderProperties());

        Set<SearchCriterion> criteria =
                searchRequest.toSearchCriteria(CustomerSearchDescriptors.getDescriptors());

        Page<Customer> page;
        if(criteria.isEmpty()) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(SearchCriteriaSpecification.buildWithAnd(criteria), pageRequest);
        }

        return PaginationResponse
                .data(customerAssembler.toResponseList(page.getContent()))
                .totalElements(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public CustomerOrdersResponse getCustomerOrders(Long customerId) {
        Customer customer = getById(customerId);
        return customerAssembler.toCustomerOrderResponse(customer.getCustomerOrders());
    }

    @Override
    public CustomerOrderResponse createCustomerOrder(Long customerId,
                                                     CustomerOrderRequest customerOrderRequest) {

        return createCustomerOrder(getById(customerId),
                                  null,
                                   customerOrderRequest);
    }

    private CustomerOrderResponse createCustomerOrder(Customer customer,
                                                      CustomerOrder customerOrder,
                                                      CustomerOrderRequest customerOrderRequest) {

        Map<Long, Integer> productQuantities  = new HashMap<>();
        for(OrderProduct orderProduct: customerOrderRequest.getProducts()) {
            productQuantities.put(orderProduct.getProductId(),
                                  orderProduct.getQuantity());
        }

        List<Product> products =
                productService.getProducts(productQuantities.keySet());

        if(products.isEmpty()) {
            throw new ApplicationException(CustomerOrderErrorCodes.EMPTY_CUSTOMER_ORDER);
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
        orderRepository.save(customerOrder);

        return customerAssembler
                .toCustomerOrderResponse(customerOrder);
    }

    @Override
    public CustomerOrderResponse updateCustomerOrder(Long customerOrderId,
                                                     CustomerOrderUpdateRequest customerOrderRequest) {

        if(customerOrderRequest.getUpdateMode() == null) {
            throw new ApplicationException(GeneralErrorCodes.INTERNAL_SERVER_ERROR);
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

    private CustomerOrderResponse appendCustomerOrder(CustomerOrder customerOrder,
                                                      CustomerOrderUpdateRequest customerOrderRequest) {

        Map<Long, Integer> productQuantities  = new HashMap<>();
        for(OrderProduct orderProduct: customerOrderRequest.getProducts()) {
            productQuantities.put(orderProduct.getProductId(),
                                  orderProduct.getQuantity());
        }

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
            if(newPrice != null && newPrice.equals(orderProduct.getPrice().getPreTax()) == false) {
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
        orderRepository.save(customerOrder);

        return customerAssembler
                .toCustomerOrderResponse(customerOrder);
    }

    @Override
    public void deleteCustomerOder(Long customerOrderId) {
        orderRepository.delete(getCustomerOrder(customerOrderId));
    }

    private void checkCustomerEmail(Long customerId, String email) {
        Customer customer = repository.getByEmail(email);
        if(customerId != null && customer.getId().equals(customerId)) {
            return;
        }
        if(customer != null ) {
            throw new ApplicationException(CustomerOrderErrorCodes.CUSTOMER_EMAIL_TAKEN);
        }
    }

    private CustomerOrder getCustomerOrder(Long customerOrderId) {
        CustomerOrder customerOrder  = orderRepository.findOne(customerOrderId);
        if(customerOrder == null) {
            throw new ApplicationException(GeneralErrorCodes.RESOURCE_NOT_FOUND);
        }
        return customerOrder;
    }
}