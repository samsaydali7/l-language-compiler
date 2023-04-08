package samsaydali.l.services.scope;

public class IdentifierException extends RuntimeException {
    String identifier;

    IdentifierException(String identifier){
        super();
        this.identifier = identifier;
    }
}
