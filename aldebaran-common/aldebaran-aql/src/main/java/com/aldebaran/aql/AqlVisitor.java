package com.aldebaran.aql;

import com.aldebaran.aql.antlr.QueryBaseVisitor;
import com.aldebaran.aql.antlr.QueryParser;
import com.aldebaran.aql.errors.ProcessingException;
import com.aldebaran.aql.nodes.ExpressionNode;
import com.aldebaran.aql.nodes.AqlNode;
import com.aldebaran.aql.nodes.WrapperNode;
import com.aldebaran.aql.processors.ValueProcessor;
import com.aldebaran.utils.enums.BooleanOperator;
import com.aldebaran.utils.enums.ComparisonOperator;
import com.aldebaran.utils.enums.OrderDirection;
import com.aldebaran.utils.EnumUtils;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


final class AqlVisitor extends QueryBaseVisitor<AqlNode> {

    private final List<ValueProcessor> processors;

    public AqlVisitor(List<ValueProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public AqlNode visitSearch(QueryParser.SearchContext ctx) {
        return traverse(ctx.expression());
    }

    OrderByClause getOrderByClause(QueryParser.SearchContext ctx) {
        if(ctx.orderByExpression() == null) {
            return new OrderByClause(new String[0], null);
        }
        Set<String> orderByProperties = new HashSet<>();

        QueryParser.OrderByExpressionContext dbCtx = ctx.orderByExpression();
        for(TerminalNode terminalNode: dbCtx.SearchProperty()) {
            orderByProperties.add(terminalNode.getText());
        }

        return new OrderByClause(
                orderByProperties.toArray(new String[orderByProperties.size()]),
                EnumUtils.getByRepresentation(OrderDirection.class, dbCtx.OrderByDirection().getText())
        );
    }

    AqlNode getTree(QueryParser.SearchContext ctx) {
        return traverse(ctx.expression());
    }

    Collection<AqlNode> getExpressions(QueryParser.SearchContext ctx) {
        Set<AqlNode> nodes = new HashSet<>();
        traverseExpressions(ctx.expression(), nodes);
        return nodes;
    }

    ParsedAqlWrapper getParsedAqlWrapper(QueryParser.SearchContext ctx) {
        return new ParsedAqlWrapper(getTree(ctx), getExpressions(ctx), getOrderByClause(ctx));
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
            return toExpressionNode(ctx);
        }
        return null;
    }

    private ExpressionNode toExpressionNode(QueryParser.ExpressionContext ctx) {
        String unprocessedValue = ctx.SearchValue().getText();
        Object searchValue = null;

        for(ValueProcessor processor: processors) {
            if(processor.shouldProcess(unprocessedValue)) {
                try {
                    searchValue = processor.process(unprocessedValue);
                } catch(Exception e) {
                    throw new ProcessingException(processor.getClass(), unprocessedValue, e);
                }
                break;
            }
        }

        if(searchValue == null) {
            searchValue = unprocessedValue;
        }

        return new ExpressionNode<>(ctx.SearchProperty().getText(),
                                    searchValue,
                                    EnumUtils.getByRepresentation(ComparisonOperator.class,
                                            ctx.ComparisonOperator().getText()));
    }

    private void traverseExpressions(QueryParser.ExpressionContext ctx, Set<AqlNode> nodes) {
        if(ctx.LPAREN() != null) {
            traverseExpressions(ctx.expression(0), nodes);
            return;
        }
        if(ctx.BooleanOperator() != null) {
            traverseExpressions(ctx.expression(0), nodes);
            traverseExpressions(ctx.expression(1), nodes);
            return;
        }
        nodes.add(toExpressionNode(ctx));
    }
}