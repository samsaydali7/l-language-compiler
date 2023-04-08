package samsaydali.l.ast.common;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Identifier {
    String name;
    Type type;
}
