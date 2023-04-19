package samsaydali.l.parser;

import org.junit.jupiter.api.Test;
import samsaydali.l.app.LCompiler;
import samsaydali.l.ast.common.*;
import samsaydali.l.ast.functions.Argument;
import samsaydali.l.ast.functions.FunctionCall;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;
import samsaydali.l.ast.variables.VariableAssign;
import samsaydali.l.ast.variables.VariableDef;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LCompilerTest {

    String input1 = "def mul[x: Int, y: Int]: Int <- x * y;\n";
    String input2 = "var x: Int <- 2;\n";
    String input3 = "var y: Int;\ny <- x * 2 + x;\n";
    String input4 = "mul[x, y+2];\n";

    @Test
    void parseInput1() {
        LCompiler app = new LCompiler();
        LVisitor v = app.visit(input1);
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
    void parseInput2() {
        LCompiler app = new LCompiler();
        LVisitor v = app.visit(input2);
        VariableDef def = (VariableDef) v.statements.get(0);

        Expression e = Expression.builder()
                .expressionType(Expression.ExpressionType.VALUE)
                .value(new Value(new Type("Int"), "2"))
                .build();

        Assign a = new Assign(new Type("Int"), e);

        Identifier x = new Identifier("x", new Type("Int"));

        VariableDef expected = VariableDef.builder().assign(a).identifier(x).initialized(true).build();

        assertThat(def).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void parseInput3() {
        LCompiler app = new LCompiler();
        LVisitor v = app.visit(input2 + input3);
        VariableDef def = (VariableDef) v.statements.get(1);
        VariableAssign variableAssign = (VariableAssign) v.statements.get(2);

        Identifier y = new Identifier("y", new Type("Int"));
        Identifier x = new Identifier("x", new Type("Int"));

        VariableDef expectedDef = VariableDef.builder().identifier(y).build();

        assertThat(def).usingRecursiveComparison().isEqualTo(expectedDef);

        VariableAssign expectedAssign = VariableAssign.builder()
                .assign(Assign.builder()
                        .expectedType(new Type("Int"))
                        .expression(Expression.builder()
                                .expressionType(Expression.ExpressionType.BINARY)
                                .op("*")
                                .lhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(x).build())
                                .rhs(Expression.builder()
                                        .expressionType(Expression.ExpressionType.BINARY)
                                        .op("+")
                                        .lhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("Int"), "2")).build())
                                        .rhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(x).build())
                                        .build())
                                .build())
                        .build())
                .identifier(y)
                .build();

        assertThat(variableAssign).usingRecursiveComparison().isEqualTo(expectedAssign);
    }

    @Test
    void parseInput4() {
        LCompiler app = new LCompiler();
        LVisitor v = app.visit(input1 + input2 + input3 + input4);
        FunctionCall call = (FunctionCall) v.statements.get(4);

        // Compare func name
        assertThat("mul").isEqualTo(call.getName());

        Identifier x = new Identifier("x", new Type("Int"));
        Identifier y = new Identifier("y", new Type("Int"));
        // Compare parameters
        List<Argument> arguments = new LinkedList(){{
            add(new Argument(0, Expression.builder().expressionType(Expression.ExpressionType.ID).ID(x).build()));
            add(new Argument(1, Expression.builder().expressionType(Expression.ExpressionType.BINARY)
                    .lhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(y).build())
                    .op("+")
                    .rhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("Int"), "2")).build())
                    .build()));
        }};

        FunctionCall expectedCall = new FunctionCall("mul", arguments);

        assertThat(call).usingRecursiveComparison().isEqualTo(expectedCall);
    }
}