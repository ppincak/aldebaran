package com.aldebaran.aql;

import com.aldebaran.aql.antlr.QueryLexer;
import com.aldebaran.aql.antlr.QueryParser;
import com.aldebaran.aql.nodes.AqlNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Collection;


public class AqlParser implements Parser {

    private final AqlVisitor visitor;

    public AqlParser() {
        this.visitor = new AqlVisitor();
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

    private QueryParser createParser(String aql) {
        QueryLexer lexer = new QueryLexer(CharStreams.fromString(aql));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        return new QueryParser(tokenStream);
    }

    @Override
    public AqlVisitor getVisitor() {
        return visitor;
    }
}
