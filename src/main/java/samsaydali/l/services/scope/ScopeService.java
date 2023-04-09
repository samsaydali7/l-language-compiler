package samsaydali.l.services.scope;

import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.functions.Argument;
import samsaydali.l.ast.functions.FunctionDef;

import java.util.List;

public class ScopeService {

    Scope currentScope = new Scope();

    public void openScope() {
        Scope newScope = new Scope();
        newScope.setParent(currentScope);
        currentScope = newScope;
    }

    public void closeScope() {
        currentScope.clear();
        currentScope = currentScope.getParent();
    }

    public void addIdentifier(Identifier identifier) throws IdentifierAlreadyInScope {
        currentScope.addIdentifier(identifier);
    }

    public Identifier getIdentifier(String id) throws IdentifierNotFoundInScope {
        return currentScope.getIdentifier(id);
    }

    public void addFunction(FunctionDef def) throws IdentifierAlreadyInScope {
        currentScope.addFunction(def);
    }

    public FunctionDef getFunctionDef(String id, List<Argument> arguments) throws IdentifierNotFoundInScope {
        return currentScope.getFunction(id, arguments);
    }

}
