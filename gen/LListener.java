// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LParser}.
 */
public interface LListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LParser#l}.
	 * @param ctx the parse tree
	 */
	void enterL(LParser.LContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#l}.
	 * @param ctx the parse tree
	 */
	void exitL(LParser.LContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#function_def}.
	 * @param ctx the parse tree
	 */
	void enterFunction_def(LParser.Function_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#function_def}.
	 * @param ctx the parse tree
	 */
	void exitFunction_def(LParser.Function_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(LParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(LParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(LParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(LParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(LParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(LParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(LParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(LParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(LParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(LParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#variable_def}.
	 * @param ctx the parse tree
	 */
	void enterVariable_def(LParser.Variable_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#variable_def}.
	 * @param ctx the parse tree
	 */
	void exitVariable_def(LParser.Variable_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#variable_assign}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assign(LParser.Variable_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#variable_assign}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assign(LParser.Variable_assignContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(LParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(LParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(LParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(LParser.ValueContext ctx);
}