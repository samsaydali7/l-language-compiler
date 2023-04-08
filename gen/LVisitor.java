// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LParser#l}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitL(LParser.LContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#function_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_def(LParser.Function_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(LParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(LParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(LParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(LParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(LParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#variable_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_def(LParser.Variable_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#variable_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_assign(LParser.Variable_assignContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(LParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(LParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(LParser.ValueContext ctx);
}