package samsaydali.l.services.scope;

public class IdentifierAlreadyInScope extends IdentifierException {

    IdentifierAlreadyInScope(String identifier) {
        super(identifier);
    }

    @Override
    public String getMessage() {
        return "Identifier " + identifier + " already in scope!";
    }
}
