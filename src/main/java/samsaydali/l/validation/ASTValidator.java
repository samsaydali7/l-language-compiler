package samsaydali.l.validation;

import lombok.*;
import samsaydali.l.ast.Statement;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.variables.VariableAssign;

import java.util.List;

@Builder @NoArgsConstructor @Setter @Getter @AllArgsConstructor
public class ASTValidator {
    private List<Statement> statements;

    public void validate() {
        statements.forEach(s -> {
            if (s instanceof VariableAssign) VariableAssignValidator.validate((VariableAssign) s);
            if (s instanceof FunctionDef) FunctionDefValidator.validate((FunctionDef) s);
        });
    }
}
