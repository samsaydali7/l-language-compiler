package samsaydali.l.services.scope;

public class IdentifierNotFoundInScope extends IdentifierException {

    IdentifierNotFoundInScope(String identifier) {
        super(identifier);
    }

    @Override
    public String getMessage() {
        return "Identifier " + identifier + " not found in scope!";
    }
}
