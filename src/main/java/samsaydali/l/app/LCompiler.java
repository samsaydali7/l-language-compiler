package samsaydali.l.app;

import freemarker.template.TemplateException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import samsaydali.l.ast.Statement;
import samsaydali.l.codegen.JavaGenerator;
import samsaydali.l.gen.LLexer;
import samsaydali.l.gen.LParser;
import samsaydali.l.parser.LVisitor;

import java.io.IOException;
import java.util.List;

public class LCompiler {

    public LVisitor visit(String lInput) {
        LLexer lexer = new LLexer(CharStreams.fromString(lInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LParser parser = new LParser(tokens);

        LVisitor visitor = new LVisitor();
        parser.l().accept(visitor);

        return visitor;
    }

    public List<Statement> getAST(LVisitor visitor) {
        return visitor.statements;
    }

    public JavaGenerator getGenerator() {
        return new JavaGenerator();
    }

    public String generateCode(List<Statement> ast) throws TemplateException, IOException {
        return getGenerator().generate(ast);
    }

    public String compileToJava(String lInput) throws TemplateException, IOException {
        LVisitor visitor = visit(lInput);
        List<Statement> ast = getAST(visitor);
        String javaCode = generateCode(ast);
        return javaCode;
    }

}
