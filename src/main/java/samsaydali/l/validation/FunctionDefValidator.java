package samsaydali.l.validation;

import samsaydali.l.ast.functions.FunctionDef;

public class FunctionDefValidator {

    public static void validate(FunctionDef functionDef) {
        if (!functionDef.getAssign().getExpectedType().equals(functionDef.getAssign().getExpression().resultType())) {
            throw new FunctionDefTypeMismatchException("FunctionDefTypeMismatchException");
        }
    }

    public static class FunctionDefTypeMismatchException extends RuntimeException {
        public FunctionDefTypeMismatchException(String message) {
            super(message);
        }
    }
}
