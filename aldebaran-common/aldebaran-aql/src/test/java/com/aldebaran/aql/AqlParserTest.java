package com.aldebaran.aql;

import com.aldebaran.aql.errors.InvalidQuerySyntaxException;
import com.aldebaran.aql.nodes.AqlNode;
import com.aldebaran.aql.nodes.ExpressionNode;
import com.aldebaran.utils.enums.ComparisonOperator;
import org.junit.Assert;
import org.junit.Test;


public class AqlParserTest {

    private final AqlParser aqlParser;

    public AqlParserTest() {
        aqlParser = new AqlParser();
    }

    @Test
    public void testSigleExpressionQuery() {
        ParsedAqlWrapper parsedAqlWrapper =
                aqlParser.toParsedAqlWrapper("name=\"test\"");

        Assert.assertEquals(1, parsedAqlWrapper.getNodes().size());

        AqlNode aqlNode = parsedAqlWrapper
                            .getNodes()
                            .iterator()
                            .next();

        Assert.assertEquals("Invalid type of root node",
                            ExpressionNode.class,
                            aqlNode.getClass());

        ExpressionNode expressionNode = (ExpressionNode) aqlNode;

        Assert.assertEquals(ComparisonOperator.EQUALS, expressionNode.getOperator());
        Assert.assertEquals("name",expressionNode.getSearchProperty());
        Assert.assertEquals("test",expressionNode.getSearchValue());
    }

    @Test
    public void testArrayExpressionQuery() {
        ParsedAqlWrapper parsedAqlWrapper =
                aqlParser.toParsedAqlWrapper("name LIKE [1,15,\"peter\"]");

        Assert.assertEquals(1, parsedAqlWrapper.getNodes().size());

        AqlNode aqlNode = parsedAqlWrapper
                .getNodes()
                .iterator()
                .next();

        Assert.assertEquals("Invalid type of root node",
                ExpressionNode.class,
                aqlNode.getClass());

        ExpressionNode<Object[]> expressionNode = (ExpressionNode<Object[]>) aqlNode;

        Assert.assertEquals(ComparisonOperator.LIKE, expressionNode.getOperator());
        Assert.assertEquals("name",expressionNode.getSearchProperty());
        Assert.assertEquals(3, expressionNode.getSearchValue().length);

    }

    @Test(expected = InvalidQuerySyntaxException.class)
    public void testRandomString() {
        aqlParser.toTree("name=\"test\" AND");
    }
}