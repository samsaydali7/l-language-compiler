package samsaydali.l.ast.variables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.Statement;
import samsaydali.l.ast.common.Assign;
import samsaydali.l.ast.common.Identifier;

@Builder @AllArgsConstructor @Setter @Getter
public class VariableAssign implements Statement {
    private Identifier identifier;
    private Assign assign;
}
