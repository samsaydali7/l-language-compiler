package samsaydali.l.ast.common;

import lombok.*;

@Builder @Setter @Getter
public class Expression {
    ExpressionType expressionType;
    Identifier ID;
    Value value;
    String op;
    Expression lhs;
    Expression rhs;

    public enum ExpressionType {
        BINARY, UNARY, ID, VALUE
    }

    // Todo: Move all below to a service
    public Type resultType() throws LhsRhsTypeMismatchException {
        switch (expressionType) {
            case VALUE: return value.type;
            case UNARY: return rhs.resultType();
            case BINARY: return getValidatedType();
            case ID: return ID.type;
        }
        return ID.type;
    }

    private Type getValidatedType() throws LhsRhsTypeMismatchException {
        if (lhs.resultType().equals(rhs.resultType())) {
            return lhs.resultType();
        }
        throw new LhsRhsTypeMismatchException("LhsRhsTypeMismatchException");
    }

    public static class LhsRhsTypeMismatchException extends RuntimeException {

        public LhsRhsTypeMismatchException(String message) {
            super(message);
        }
    }
}
