grammar L;

l: statement*
    ;

statement: function_def
    | variable_def
    | variable_assign
    | function_call
    ;

function_def : 'def' ID '[' parameters? ']' ':' type assign SC
         ;

parameters : parameter (',' parameter)*
           ;

parameter : ID ':' type
          ;

function_call : ID '[' arguments? ']' SC
         ;

arguments : argument (',' argument)*
           ;

argument : expression
          ;

variable_def : 'var' ID ':' type assign? SC
         ;

variable_assign : ID assign SC
         ;

assign: '<-' expression
        ;

type : 'Int' | 'String' | 'Boolean' | ID
     ;

expression : value
           | ID
           | '(' expression ')'
           | expression '+' expression
           | expression '-' expression
           | expression '*' expression
           | expression '/' expression
           | expression '>' expression
           | expression '<' expression
           | expression '>=' expression
           | expression '<=' expression
           | expression '==' expression
           | expression '!=' expression
           | '!' expression
           | expression '&&' expression
           | expression '||' expression
           ;

value : INT
      | STRING
      | TRUE
      | FALSE
      ;

INT : ('0'..'9')+
    ;

STRING : '"' (ESC | ~["\\])* '"'
       ;

fragment ESC : '\\' ["\\/bfnrt]
            ;

TRUE : 'true'
     ;

FALSE : 'false'
      ;

ID : [a-zA-Z][a-zA-Z0-9]*
   ;

SC : ';'
     ;

WS : [ \t\r\n]+ -> skip
   ;