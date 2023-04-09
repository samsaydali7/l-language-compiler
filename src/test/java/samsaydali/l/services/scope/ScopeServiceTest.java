package samsaydali.l.services.scope;

import org.junit.jupiter.api.Test;
import samsaydali.l.ast.common.*;
import samsaydali.l.ast.functions.Argument;
import samsaydali.l.ast.functions.FunctionCall;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ScopeServiceTest {

    Identifier x1 = new Identifier("x", new Type("String"));
    Identifier x2 = new Identifier("x", new Type("Int"));
    Identifier y1 = new Identifier("y", new Type("String"));

    @Test
    public void canReadIdentifierFromScope() {
        ScopeService scopeService = new ScopeService();

        scopeService.addIdentifier(x1);

        Identifier result = scopeService.getIdentifier("x");

        assertAll(() -> {
            assertEquals(result.getName(), x1.getName());
            assertEquals(result.getType(), x1.getType());
        });

        assertThrows(IdentifierNotFoundInScope.class, () -> {
            scopeService.getIdentifier("y");
        });
    }

    @Test
    public void throwsErrorForDuplication() {
        ScopeService scopeService = new ScopeService();

        assertThrows(IdentifierAlreadyInScope.class, () -> {
            scopeService.addIdentifier(x1);
            scopeService.addIdentifier(x2);
        });
    }

    @Test
    public void addsToInnerScope() {
        ScopeService scopeService = new ScopeService();

        assertAll(() -> {
            scopeService.addIdentifier(x1);
            Identifier result1 = scopeService.getIdentifier("x");
            assertEquals(result1.getType(), x1.getType());

            scopeService.openScope();
            scopeService.addIdentifier(x2);
            scopeService.addIdentifier(y1);
            Identifier result2 = scopeService.getIdentifier("x");
            Identifier result3 = scopeService.getIdentifier("y");
            assertEquals(result2.getType(), x2.getType());
            assertEquals(result3.getType(), y1.getType());


            scopeService.closeScope();
            Identifier result4 = scopeService.getIdentifier("x");
            assertEquals(result4.getType(), x1.getType());
            assertThrows(IdentifierNotFoundInScope.class, () -> {
                scopeService.getIdentifier("y");
            });
        });
    }

    @Test
    public void canAddFunctionAndValidate() {
        ScopeService scopeService = new ScopeService();

        Identifier a = new Identifier("a", new Type("Int"));
        Identifier b = new Identifier("b", new Type("Int"));
        Identifier a1 = new Identifier("b", new Type("Int"));
        Identifier b1 = new Identifier("b", new Type("Int"));

        FunctionDef def = new FunctionDef("fun", new LinkedList<Parameter>(){{
            add(new Parameter(a, 0));
            add(new Parameter(b, 1));
        }}, Assign.builder().build());

        scopeService.addFunction(def);

        FunctionCall call = new FunctionCall("fun", new LinkedList<Argument>(){{
            add(new Argument(0, Expression.builder()
                    .expressionType(Expression.ExpressionType.BINARY)
                    .op("+")
                    .lhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("Int"), "2")).build())
                    .rhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(a1).build())
                    .build()));
            add(new Argument(1, Expression.builder()
                    .expressionType(Expression.ExpressionType.ID)
                    .ID(b1)
                    .build()));
        }});

        assertAll(() -> {
            scopeService.getFunctionDef(call.getName(), call.getArguments());
        });
    }

    @Test
    public void canAddFunctionAndThrowForArgumentSize() {
        ScopeService scopeService = new ScopeService();

        Identifier a = new Identifier("a", new Type("Int"));
        Identifier b = new Identifier("b", new Type("Int"));
        Identifier a1 = new Identifier("b", new Type("Int"));
        Identifier b1 = new Identifier("b", new Type("Int"));

        FunctionDef def = new FunctionDef("fun", new LinkedList<Parameter>(){{
            add(new Parameter(a, 0));
            add(new Parameter(b, 1));
        }}, Assign.builder().build());

        scopeService.addFunction(def);

        FunctionCall call = new FunctionCall("fun", new LinkedList<Argument>(){{
            add(new Argument(0, Expression.builder()
                    .expressionType(Expression.ExpressionType.BINARY)
                    .op("+")
                    .lhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("Int"), "2")).build())
                    .rhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(a1).build())
                    .build()));
        }});

        assertThrows(ArgumentsNotCompliantWithParametersList.class, () -> {
            scopeService.getFunctionDef(call.getName(), call.getArguments());
        });
    }

    @Test
    public void canAddFunctionAndThrowForArgumentNonCompliant() {
        ScopeService scopeService = new ScopeService();

        Identifier a = new Identifier("a", new Type("Int"));
        Identifier b = new Identifier("b", new Type("Int"));
        Identifier a1 = new Identifier("b", new Type("Int"));
        Identifier b1 = new Identifier("b", new Type("String"));

        FunctionDef def = new FunctionDef("fun", new LinkedList<Parameter>(){{
            add(new Parameter(a, 0));
            add(new Parameter(b, 1));
        }}, Assign.builder().build());

        scopeService.addFunction(def);

        FunctionCall call = new FunctionCall("fun", new LinkedList<Argument>(){{
            add(new Argument(0, Expression.builder()
                    .expressionType(Expression.ExpressionType.BINARY)
                    .op("+")
                    .lhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("Int"), "2")).build())
                    .rhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(a1).build())
                    .build()));
            add(new Argument(1, Expression.builder()
                    .expressionType(Expression.ExpressionType.ID)
                    .ID(b1)
                    .build()));
        }});

        assertThrows(ArgumentsNotCompliantWithParametersList.class, () -> {
            scopeService.getFunctionDef(call.getName(), call.getArguments());
        });
    }

    @Test
    public void canAddFunctionAndThrowForExpressionTypeMismatch() {
        ScopeService scopeService = new ScopeService();

        Identifier a = new Identifier("a", new Type("Int"));
        Identifier b = new Identifier("b", new Type("Int"));
        Identifier a1 = new Identifier("b", new Type("Int"));
        Identifier b1 = new Identifier("b", new Type("Int"));

        FunctionDef def = new FunctionDef("fun", new LinkedList<Parameter>(){{
            add(new Parameter(a, 0));
            add(new Parameter(b, 1));
        }}, Assign.builder().build());

        scopeService.addFunction(def);

        FunctionCall call = new FunctionCall("fun", new LinkedList<Argument>(){{
            add(new Argument(0, Expression.builder()
                    .expressionType(Expression.ExpressionType.BINARY)
                    .op("+")
                    .lhs(Expression.builder().expressionType(Expression.ExpressionType.VALUE).value(new Value(new Type("String"), "2")).build())
                    .rhs(Expression.builder().expressionType(Expression.ExpressionType.ID).ID(a1).build())
                    .build()));
            add(new Argument(1, Expression.builder()
                    .expressionType(Expression.ExpressionType.ID)
                    .ID(b1)
                    .build()));
        }});

        assertThrows(Expression.LhsRhsTypeMismatchException.class, () -> {
            scopeService.getFunctionDef(call.getName(), call.getArguments());
        });
    }
}