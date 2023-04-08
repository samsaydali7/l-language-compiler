package samsaydali.l.services.scope;

import samsaydali.l.ast.common.Identifier;

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

}
