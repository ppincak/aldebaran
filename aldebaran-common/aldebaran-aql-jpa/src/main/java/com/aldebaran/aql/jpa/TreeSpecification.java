package com.aldebaran.aql.jpa;

import com.aldebaran.aql.nodes.ExpressionNode;
import com.aldebaran.aql.nodes.AqlNode;
import com.aldebaran.aql.nodes.WrapperNode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;


public class TreeSpecification<T, Y extends Comparable<Y>> implements Specification<T> {

    private final ExpressionNode<Y> expressionNode;

    private TreeSpecification(ExpressionNode<Y> expressionNode) {
        this.expressionNode = expressionNode;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (expressionNode.getOperator()) {
            case EQUALS:
                return builder.equal(root.get(expressionNode.getSearchProperty()),
                                              expressionNode.getSearchValue());
            case NOT_EQUALS:
                return builder.notEqual(root.get(expressionNode.getSearchProperty()),
                        expressionNode.getSearchValue());
            case LESS_THAN:
                return builder.lessThan(root.get(expressionNode.getSearchProperty()),
                                                 expressionNode.getSearchValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(expressionNode.getSearchProperty()),
                                                    expressionNode.getSearchValue());
            case LESS_THAN_EQUALS:
                return builder.lessThanOrEqualTo(root.get(expressionNode.getSearchProperty()),
                                                          expressionNode.getSearchValue());
            case GREATER_THAN_EQUALS:
                return builder.greaterThanOrEqualTo(root.get(expressionNode.getSearchProperty()),
                                                             expressionNode.getSearchValue());
            case IN:
                return root
                        .get(expressionNode.getSearchProperty())
                        .in((Collection<?>) expressionNode.getSearchValue());
            case LIKE:
                return builder.like(root.get(expressionNode.getSearchProperty()),
                        "%" + expressionNode.getSearchValue().toString() + "%");
            case ILIKE:
                return builder.like(root.get(expressionNode.getSearchProperty()),
                        "%" + expressionNode.getSearchValue().toString().toLowerCase() + "%");
            default:
                return null;
        }
    }


    @SuppressWarnings("unchecked")
    private static <T, Y extends Comparable<Y>> Specifications<T> traverse(AqlNode node) {
        if(node instanceof WrapperNode) {
            WrapperNode wrapperNode = (WrapperNode) node;

            Specifications lSpecifications =
                    traverse(wrapperNode.getlNode());

            Specifications rSpecifications = traverse(wrapperNode.getrNode());

            switch (wrapperNode.getOperator()) {
                case AND:
                    return lSpecifications.and(rSpecifications);
                case OR:
                    return lSpecifications.or(rSpecifications);
            }
        }
        if(node instanceof ExpressionNode) {
            return Specifications.where(new TreeSpecification<T, Y>((ExpressionNode<Y>) node));
        }
        return null;
    }

    public static <T> Specification<T> build(AqlNode rootNode) {
        return traverse(rootNode);
    }
}