package samsaydali.l.codegen;

public class Main {

    public static void main(String[] args) {
    <#list statements as statement>
        <#if statement.getType() == "variable_def">
            ${statement.identifier.type.type} ${statement.identifier.name}<#if statement.initialized??> = ${statement.assign.expression.getText()}</#if>;
        </#if>
        <#if statement.getType() == "variable_assign">
            ${statement.identifier.name} = ${statement.assign.expression.getText()};
        </#if>
        <#if statement.getType() == "function_call">
            ${statement.name}(${statement.arguments?map(a -> a.expression.getText())?join(', ')?no_esc});
        </#if>
    </#list>

    }

    <#list statements?filter(s -> s.getType() == 'function_def') as function>
    private static int ${function.name}(${function.parameters?map(p -> p.identifier.type.type + " " + p.identifier.name)?join(', ')?no_esc}) {
        return ${function.assign.expression.getText()};
    }
    </#list>
}