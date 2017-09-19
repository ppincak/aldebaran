package com.aldebaran.aql;

import com.aldebaran.aql.nodes.AqlNode;

import java.util.Collection;


public class ParsedAqlWrapper {

    private final AqlNode rootNode;
    private final Collection<AqlNode> nodes;
    private final OrderByClause orderByClause;

    public ParsedAqlWrapper(AqlNode rootNode,
                            Collection<AqlNode> nodes,
                            OrderByClause orderByClause) {
        this.rootNode = rootNode;
        this.nodes = nodes;
        this.orderByClause = orderByClause;
    }

    public AqlNode getRootNode() {
        return rootNode;
    }

    public OrderByClause getOrderByClause() {
        return orderByClause;
    }
}
