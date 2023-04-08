package samsaydali.l.services;

import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.functions.FunctionDef;

import java.util.LinkedList;
import java.util.List;

public class Scope {

    List<Identifier> identifiers = new LinkedList<>();
    List<FunctionDef> functionDefs = new LinkedList<>();

    public void addIdentifier(Identifier identifier) {
        identifiers.add(identifier);
    }

    public void addFunctionDef(FunctionDef functionDef) {
        functionDefs.add(functionDef);
    }

    // Todo: Throw exception in case not found
    public Identifier getIdentifier(String id) {
        Identifier identifier = identifiers.stream().filter(i -> i.getName().equals(id)).findFirst().get();
        return identifier;
    }

    // Todo: Throw exception in case not found
    public FunctionDef getFuncDef(String id) {
        FunctionDef functionDef = functionDefs.stream().filter(f -> f.getName().equals(id)).findFirst().get();
        return functionDef;
    }
}
