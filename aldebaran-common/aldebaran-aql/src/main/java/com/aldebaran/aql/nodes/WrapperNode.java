package com.aldebaran.aql.nodes;

import com.aldebaran.utils.operators.BooleanOperator;


public class WrapperNode implements AqlNode {

    private final AqlNode lNode;
    private final AqlNode rNode;
    private final BooleanOperator operator;

    public WrapperNode(AqlNode lNode, AqlNode rNode, BooleanOperator operator) {
        this.lNode = lNode;
        this.rNode = rNode;
        this.operator = operator;
    }

    public AqlNode getlNode() {
        return lNode;
    }

    public AqlNode getrNode() {
        return rNode;
    }

    public BooleanOperator getOperator() {
        return operator;
    }
}