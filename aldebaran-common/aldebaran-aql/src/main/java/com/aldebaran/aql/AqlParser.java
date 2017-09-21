package com.aldebaran.aql;

import com.aldebaran.aql.antlr.QueryLexer;
import com.aldebaran.aql.antlr.QueryParser;
import com.aldebaran.aql.errors.FailFastErrorStrategy;
import com.aldebaran.aql.nodes.AqlNode;
import org.antlr.v4.runtime.*;

import java.util.Collection;


public class AqlParser implements Parser {

    private final AqlVisitor visitor;
    private final ANTLRErrorStrategy errorStrategy;

    public AqlParser() {
        this.visitor = new AqlVisitor();
        this.errorStrategy = new FailFastErrorStrategy();
    }

    @Override
    public AqlNode toTree(String aql) {
        return visitor.getTree(createParser(aql).search());
    }

    @Override
    public Collection<AqlNode> toNodeCollection(String aql) {
        return visitor.getExpressions(createParser(aql).search());
    }

    @Override
    public ParsedAqlWrapper toParsedAqlWrapper(String aql) {
        return visitor.getParsedAqlWrapper(createParser(aql).search());
    }

    @Override
    public AqlVisitor getVisitor() {
        return visitor;
    }

    private QueryParser createParser(String aql) {
        QueryLexer lexer = new QueryLexer(CharStreams.fromString(aql));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QueryParser queryParser = new QueryParser(tokenStream);
        queryParser.setErrorHandler(errorStrategy);
        return queryParser;
    }
}
