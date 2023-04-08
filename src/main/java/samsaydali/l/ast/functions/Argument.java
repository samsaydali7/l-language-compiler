package samsaydali.l.ast.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.common.Expression;

@Builder @AllArgsConstructor @Setter @Getter
public class Argument {
    Integer index;
    Expression expression;
}
