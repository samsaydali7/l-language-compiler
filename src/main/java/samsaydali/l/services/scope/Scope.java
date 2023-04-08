package samsaydali.l.services.scope;

import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.common.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Setter @Getter
public class Scope {

    List<Identifier> identifiers = new LinkedList<>();
    Scope parent = null;

    public void addIdentifier(Identifier identifier) {
        Optional<Identifier> check = identifiers.stream().filter(i -> i.getName().equals(identifier.getName())).findFirst();
        if (check.isPresent()) {
            throw new IdentifierAlreadyInScope(identifier.getName());
        }
        identifiers.add(identifier);
    }

    public Identifier getIdentifier(String id) throws IdentifierNotFoundInScope {
        Optional<Identifier> identifier = identifiers.stream().filter(i -> i.getName().equals(id)).findFirst();
        if (identifier.isPresent()) {
            return identifier.get();
        }
        if (parent != null) {
            return parent.getIdentifier(id);
        }
        throw new IdentifierNotFoundInScope(id);
    }

    public void clear() {
        identifiers.clear();
    }
}
