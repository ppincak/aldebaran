package com.aldebaran.aql;

import com.aldebaran.aql.antlr.QueryLexer;
import com.aldebaran.aql.antlr.QueryParser;
import com.aldebaran.aql.nodes.AqlNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class AqlParser implements Parser {

    private final AqlVisitor visitor;

    public AqlParser() {
        this.visitor = new AqlVisitor();
    }

    public AqlNode parse(String aql) {
        QueryLexer lexer = new QueryLexer(CharStreams.fromString(aql));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QueryParser queryParser = new QueryParser(tokenStream);

        return visitor.visitSearch(queryParser.search());
    }
}
