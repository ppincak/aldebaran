package com.aldebaran.aql;

import com.aldebaran.aql.nodes.AqlNode;

import java.util.Collection;

public interface Parser {
    AqlNode toTree(String aql);

    Collection<AqlNode> toNodeCollection(String aql);

    ParsedAqlWrapper toParsedAqlWrapper(String aql);

    AqlVisitor getVisitor();
}
