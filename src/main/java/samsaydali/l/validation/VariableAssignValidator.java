package samsaydali.l.validation;

import samsaydali.l.ast.variables.VariableAssign;

public class VariableAssignValidator {

    public static void validate(VariableAssign variableAssign) {
        if (!variableAssign.getAssign().getExpectedType().equals(variableAssign.getAssign().getExpression().resultType())) {
            throw new VariableAssignTypeMismatchException("VariableAssignTypeMismatchException");
        }
    }

    public static class VariableAssignTypeMismatchException extends RuntimeException {
        public VariableAssignTypeMismatchException(String message) {
            super(message);
        }
    }
}
