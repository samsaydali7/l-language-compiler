package samsaydali.l.ast.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder @Setter @Getter
public class Type {

    String type;

    public Type(String type) {
        this.type = type.toLowerCase();
    }

    /*public enum PredefinedTypes {
        INT, BOOL, STRING
    }

    public Type(PredefinedTypes predefinedTypes) {
        switch (predefinedTypes) {
            case INT: this.type = "Int"; break;
            case STRING: this.type = "String"; break;
            case BOOL: this.type = "Bool"; break;
        }
    }*/

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((Type) obj).type, this.type);
    }
}
