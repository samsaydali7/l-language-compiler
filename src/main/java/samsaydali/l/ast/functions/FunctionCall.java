package samsaydali.l.ast.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import samsaydali.l.ast.Statement;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class FunctionCall implements Statement {
    String name;
    List<Argument> arguments;
}
