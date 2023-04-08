package samsaydali.l.parser;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.common.Type;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class LParserAppTest {

    String input1 = "def mul[x: Int, y: Int]: Int <- x * y;\n";
    String input2 = "var x: Int <- 2;\n";
    String input3 = "var y: Int;\ny <- x * (2 + x);\n";
    String input4 = "mul[x, (y+2)];\n";

    @Test
    void parseInput() {
        LParserApp app = new LParserApp();
        LVisitor v = app.parse(input1);
        FunctionDef functionDef = (FunctionDef) v.statements.get(0);
        assertEquals("mul", functionDef.getName());
        List<Parameter> parameters = new LinkedList(){{
            add(new Parameter(new Identifier("x", new Type("Int")), 0));
            add(new Parameter(new Identifier("y", new Type("Int")), 1));
        }};
        assertThat(parameters, CoreMatchers.hasItems(functionDef.getParameters()));
    }

    @Test
    void parseSuccessfully() {
        LParserApp app = new LParserApp();
        assertAll(() -> {
            app.parse(input1+input2+input3+input4);
        });
    }
}