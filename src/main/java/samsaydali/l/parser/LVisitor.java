package samsaydali.l.parser;

import samsaydali.l.ast.Statement;
import samsaydali.l.ast.common.*;
import samsaydali.l.ast.functions.Argument;
import samsaydali.l.ast.functions.FunctionCall;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;
import samsaydali.l.ast.variables.VariableAssign;
import samsaydali.l.ast.variables.VariableDef;
import samsaydali.l.gen.LBaseVisitor;
import samsaydali.l.gen.LParser;
import samsaydali.l.services.scope.ScopeService;

import java.util.LinkedList;
import java.util.List;

public class LVisitor extends LBaseVisitor {

    public List<Statement> statements = new LinkedList<>();
    ScopeService scope = new ScopeService();

    @Override
    public Object visitStatement(LParser.StatementContext ctx) {
        Object result = super.visitStatement(ctx);
        statements.add((Statement) result);
        return result;
    }

    @Override
    public Statement visitVariable_def(LParser.Variable_defContext ctx) {
        VariableDef.VariableDefBuilder builder = VariableDef.builder();

        // Add the identifier
        Identifier identifier = new Identifier(ctx.ID().getText(), new Type(ctx.type().getText()));
        scope.addIdentifier(identifier);
        builder.identifier(identifier);

        // Add the assign if exists
        if (ctx.assign() != null) {
            Assign assign = buildAssign(ctx.assign(), identifier.getType());
            builder.initialized(true);
            builder.assign(assign);
        }

        return builder.build();
    }

    @Override
    public Statement visitVariable_assign(LParser.Variable_assignContext ctx) {
        Identifier identifier = scope.getIdentifier(ctx.ID().getText());
        Assign assign = buildAssign(ctx.assign(), identifier.getType());
        return new VariableAssign(identifier, assign);
    }

    @Override
    public Statement visitFunction_def(LParser.Function_defContext ctx) {
        String name = ctx.ID().getText();
        List<Parameter> parameters = new LinkedList<>();
        scope.openScope();
        for (int i = 0; i < ctx.parameters().parameter().size(); i++) {
            LParser.ParameterContext pc = ctx.parameters().parameter().get(i);
            Identifier identifier = new Identifier(pc.ID().getText(), new Type(pc.type().getText()));
            scope.addIdentifier(identifier);
            parameters.add(new Parameter(identifier, i));
        }
        Assign assign = buildAssign(ctx.assign(), new Type(ctx.type().getText()));
        scope.closeScope();
        FunctionDef def = new FunctionDef(name, parameters, assign);
        scope.addFunction(def);
        return def;
    }

    @Override
    public Statement visitFunction_call(LParser.Function_callContext ctx) {
        String name = ctx.ID().getText();
        List<Argument> arguments = new LinkedList<>();
        for (int i = 0; i < ctx.arguments().argument().size(); i++) {
            LParser.ArgumentContext arg = ctx.arguments().argument().get(i);
            Expression expression = buildExpression(arg.expression());
            arguments.add(new Argument(i, expression));
        }
        // Validate function exists and it's arguments
        scope.getFunctionDef(name, arguments);
        return new FunctionCall(name, arguments);
    }

    private Assign buildAssign(LParser.AssignContext ctx, Type type) {
        return new Assign(type, buildExpression(ctx.expression()));
    }

    private Expression buildExpression(LParser.ExpressionContext ctx) {

        if (ctx.value() != null) {
            return getValueExpression(ctx.value());
        } else if (ctx.ID() != null) {
            return getIDExpression(ctx.ID().getText());
        } else if (ctx.getChildCount() == 3) {
            Expression left = buildExpression(ctx.expression(0));
            Expression right = buildExpression(ctx.expression(1));
            String operator = ctx.getChild(1).getText();
            return getBinaryExpression(operator, left, right);
        } else if (ctx.getChildCount() == 2) {
            String operator = ctx.getChild(0).getText();
            Expression right = buildExpression(ctx.expression(0));
            return getUnaryExpression(operator, right);
        } else {
            return null;
        }
    }

    private Expression getUnaryExpression(String op, Expression rhs) {
        Expression.ExpressionBuilder builder = Expression.builder();
        builder
                .expressionType(Expression.ExpressionType.UNARY)
                .op(op)
                .rhs(rhs);
        return builder.build();
    }

    private Expression getBinaryExpression(String op, Expression lhs, Expression rhs) {
        Expression.ExpressionBuilder builder = Expression.builder();
        builder
                .expressionType(Expression.ExpressionType.BINARY)
                .op(op)
                .lhs(lhs)
                .rhs(rhs);
        return builder.build();
    }

    private Expression getIDExpression(String name) {
        Expression.ExpressionBuilder builder = Expression.builder();
        builder
                .expressionType(Expression.ExpressionType.ID)
                .ID(scope.getIdentifier(name));
        return builder.build();
    }

    private Expression getValueExpression(LParser.ValueContext context) {
        Expression.ExpressionBuilder builder = Expression.builder();
        builder.expressionType(Expression.ExpressionType.VALUE)
                .value(new Value(getValueExpressionType(context), context.getText()));

        return builder.build();
    }

    private Type getValueExpressionType(LParser.ValueContext context) {
        if (context.STRING() != null) return new Type("String");
        else if (context.INT() != null) return new Type("Int");
        else if (context.TRUE() != null || context.FALSE() != null) return new Type("Bool");
        else return new Type("String");
    }
}
