grammar Query;

@header {
    package com.aldebaran.aql.antlr;
}

search  : expression WS? orderByExpression? EOF ;

expression
    : expression BooleanOperator expression
    | expression WS? BooleanOperator WS? expression
    | SearchProperty ComparisonOperator SearchValue
    | LPAREN expression RPAREN
    ;

orderByExpression
    : ORDERBY WS? (SearchProperty ','?)+ OrderByDirection
    ;

/**
 * Order statement tokens
 */

ORDERBY
    : 'order by'
    | 'ORDER BY'
    ;

OrderByDirection
    : ASC
    | DESC
    ;

ASC:    'asc' | 'ASC';
DESC:   'desc' | 'DESC';

/*
 * Boolean operator tokens
 */

BooleanOperator
    : AND
    | OR
    | NOT
    ;

AND : ('and' | 'AND') ;
OR  : ('or' |  'OR') ;
NOT : ('not' | 'NOT') ;

/*
 * Comparison operator tokens
 */

ComparisonOperator
    : EQ
    | NEQ
    | LT
    | LTEQ
    | GT
    | GTEQ
    | LIKE
    ;

EQ      : '=' ;
NEQ     : '!=';
LT      : '<';
LTEQ    : '<=';
GT      : '>';
GTEQ    : '>=';
IN      : ('in' | 'IN');
LIKE    : ('like' | 'LIKE') ;

SearchValue
    : NullLiteral
    | BooleanLiteral
    | IntegerLiteral
    | FloatLiteral
    | StringLiteral;

fragment
Sign
    :   [+-]
    ;

StringLiteral
    :   '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    ;

fragment
EscapeSequence
    :   '\\' [btnfr"'\\]
    ;

NullLiteral
    : 'null'
    | 'NULL'
    ;

BooleanLiteral
    : 'true'
    | 'TRUE'
    | 'false'
    | 'FALSE';

IntegerLiteral
    : NumberDigit+
    ;

FloatLiteral
    : NumberDigit+ '.' NumberDigit+;

fragment
NumberDigit
    : [0-9]
    ;

SearchProperty
    : PropertyCharacters
    ;

fragment
PropertyCharacters
    : PropertyCharacter+
    ;

fragment
PropertyCharacter
    : ~[ '"\\?!=(),]
    ;

LPAREN          : '(';
RPAREN          : ')';

WS :  [ \t\r\n\u000C]+ -> skip
   ;