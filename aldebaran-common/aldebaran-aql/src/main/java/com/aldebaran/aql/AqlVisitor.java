package com.aldebaran.aql;

import com.aldebaran.aql.antlr.QueryBaseVisitor;
import com.aldebaran.aql.antlr.QueryParser;
import com.aldebaran.aql.nodes.ExpressionNode;
import com.aldebaran.aql.nodes.AqlNode;
import com.aldebaran.aql.nodes.WrapperNode;
import com.aldebaran.aql.operators.BooleanOperator;
import com.aldebaran.aql.operators.ComparisonOperator;
import com.aldebaran.utils.EnumUtils;


public final class AqlVisitor extends QueryBaseVisitor<AqlNode> {

    @Override
    public AqlNode visitSearch(QueryParser.SearchContext ctx) {
        return traverse(ctx.expression().get(0));
    }

    private AqlNode traverse(QueryParser.ExpressionContext ctx) {
        if(ctx.LPAREN() != null) {
            return traverse(ctx.expression(0));
        }

        if(ctx.BooleanOperator() != null) {
            AqlNode lNode = traverse(ctx.expression(0));
            AqlNode rNode = traverse(ctx.expression(1));
            return new WrapperNode(lNode,
                                   rNode,
                                   EnumUtils.getByRepresentation(BooleanOperator.class,
                                                                 ctx.BooleanOperator().getText()));
        }

        if(ctx.SearchProperty() != null) {
            // TODO refactor handling
            String searchValue = ctx.SearchValue().getText();
            if(searchValue.charAt(0) == '"') {
                searchValue = searchValue.substring(1, searchValue.length() -1);
            }

            return new ExpressionNode<>(ctx.SearchProperty().getText(),
                                        searchValue,
                                        EnumUtils.getByRepresentation(ComparisonOperator.class,
                                                                      ctx.ComparisonOperator().getText()));
        }
        return null;
    }
}