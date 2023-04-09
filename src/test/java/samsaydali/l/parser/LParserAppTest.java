package samsaydali.l.parser;

import org.junit.jupiter.api.Test;
import samsaydali.l.ast.common.Assign;
import samsaydali.l.ast.common.Expression;
import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.common.Type;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LParserAppTest {

    String input1 = "def mul[x: Int, y: Int]: Int <- x * y;\n";
    String input2 = "var x: Int <- 2;\n";
    String input3 = "var y: Int;\ny <- x * (2 + x);\n";
    String input4 = "mul[x, (y+2)];\n";

    @Test
    void parseInput1() {
        LParserApp app = new LParserApp();
        LVisitor v = app.parse(input1);
        FunctionDef functionDef = (FunctionDef) v.statements.get(0);

        // Compare func name
        assertThat("mul").isEqualTo(functionDef.getName());

        Identifier x = new Identifier("x", new Type("Int"));
        Identifier y = new Identifier("y", new Type("Int"));
        // Compare parameters
        List<Parameter> parameters = new LinkedList(){{
            add(new Parameter(x, 0));
            add(new Parameter(y, 1));
        }};
        assertThat(functionDef.getParameters()).usingRecursiveFieldByFieldElementComparator().hasSameElementsAs(parameters);
        // Compare assign
        Expression e = Expression.builder()
                .expressionType(Expression.ExpressionType.BINARY)
                .op("*")
                .lhs(
                        Expression.builder()
                                .expressionType(Expression.ExpressionType.ID)
                                .ID(x)
                                .build()
                )
                .rhs(
                        Expression.builder()
                                .expressionType(Expression.ExpressionType.ID)
                                .ID(y)
                                .build()
                )
                .build();
        Assign a = new Assign(new Type("Int"), e);
        assertThat(functionDef.getAssign()).usingRecursiveComparison().isEqualTo(a);
    }

    @Test
    void parseSuccessfully() {
        LParserApp app = new LParserApp();
        assertAll(() -> {
            app.parse(input1+input2+input3+input4);
        });
    }
}