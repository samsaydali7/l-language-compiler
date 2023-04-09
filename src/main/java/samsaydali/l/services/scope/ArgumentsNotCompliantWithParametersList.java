package samsaydali.l.services.scope;

public class ArgumentsNotCompliantWithParametersList extends IdentifierException {

    ArgumentsNotCompliantWithParametersList(String identifier) {
        super(identifier);
    }

    @Override
    public String getMessage() {
        return "Call to " + identifier + " with non-compliant arguments!";
    }
}
