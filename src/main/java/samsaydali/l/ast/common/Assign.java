package samsaydali.l.ast.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @AllArgsConstructor @Setter @Getter
public class Assign {
    Type expectedType;
    Expression expression;
}
