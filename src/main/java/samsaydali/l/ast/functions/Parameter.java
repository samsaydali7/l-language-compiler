package samsaydali.l.ast.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.common.Identifier;

@Builder @AllArgsConstructor @Setter @Getter
public class Parameter {
    Identifier identifier;
    Integer index;
}
