package samsaydali.l.services.scope;

import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.functions.Argument;
import samsaydali.l.ast.functions.FunctionDef;
import samsaydali.l.ast.functions.Parameter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Setter @Getter
public class Scope {

    List<Identifier> identifiers = new LinkedList<>();
    List<FunctionDef> functions = new LinkedList<>();
    Scope parent = null;

    public void addIdentifier(Identifier identifier) {
        Optional<Identifier> check = identifiers.stream().filter(i -> i.getName().equals(identifier.getName())).findFirst();
        if (check.isPresent()) {
            throw new IdentifierAlreadyInScope(identifier.getName());
        }
        identifiers.add(identifier);
    }

    public void addFunction(FunctionDef functionDef) {
        Optional<FunctionDef> check = functions.stream().filter(i -> i.getName().equals(functionDef.getName())).findFirst();
        if (check.isPresent()) {
            throw new IdentifierAlreadyInScope(functionDef.getName());
        }
        functions.add(functionDef);
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

    public FunctionDef getFunction(String id, List<Argument> arguments) throws IdentifierNotFoundInScope {
        Optional<FunctionDef> function = functions.stream().filter(i -> i.getName().equals(id)).findFirst();
        if (function.isPresent()) {
            List<Parameter> parameters = function.get().getParameters();
            if (!(arguments.size() == parameters.size())) {
                // Validate argument count matching parameter count.
                throw new ArgumentsNotCompliantWithParametersList(id);
            }
            for (int i = 0; i < parameters.size(); i++) {
                // Validate argument types matching parameter types.
                if (!arguments.get(i).getExpression().resultType().equals(parameters.get(i).getIdentifier().getType())) {
                    throw new ArgumentsNotCompliantWithParametersList(id);
                }
            }
            return function.get();
        }
        if (parent != null) {
            return parent.getFunction(id, arguments);
        }
        throw new IdentifierNotFoundInScope(id);
    }

    public void clear() {
        identifiers.clear();
    }
}
