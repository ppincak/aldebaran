package com.aldebaran.aql.jpa;

import com.aldebaran.aql.nodes.ExpressionNode;
import com.aldebaran.aql.nodes.AqlNode;
import com.aldebaran.aql.nodes.WrapperNode;
import com.aldebaran.data.spring.AbstractSearchableSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;


public class TreeSpecification<T, Y extends Comparable<Y>>
        extends AbstractSearchableSpecification<T, Y> implements Specification<T> {

    private TreeSpecification(ExpressionNode<Y> expressionNode) {
        super(expressionNode);
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