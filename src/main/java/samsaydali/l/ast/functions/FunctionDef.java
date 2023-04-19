package samsaydali.l.ast.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.Statement;
import samsaydali.l.ast.common.Assign;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class FunctionDef implements Statement {
    String name;
    List<Parameter> parameters;
    Assign assign;

    @Override
    public String getType() {
        return "function_def";
    }
}
