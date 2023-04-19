package samsaydali.l.ast.variables;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.Statement;
import samsaydali.l.ast.common.Assign;
import samsaydali.l.ast.common.Identifier;

@Builder @Setter @Getter
public class VariableDef implements Statement {
    private Identifier identifier;
    private Boolean initialized;
    private Assign assign;

    @Override
    public String getType() {
        return "variable_def";
    }
}
