package samsaydali.l.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import samsaydali.l.gen.LLexer;
import samsaydali.l.gen.LParser;

public class LParserApp {

    public LVisitor parse(String lInput) {
        LLexer lexer = new LLexer(CharStreams.fromString(lInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LParser parser = new LParser(tokens);

        LVisitor visitor = new LVisitor();
        parser.statement().accept(visitor);

        return visitor;
    }

}
