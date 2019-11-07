/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

/**
 *
 * @author unknown
 */
public class Token {

    public byte kind;
    public String spelling;
    public int row;
    public int col;

    public Token(byte kind, String spelling, int row, int col) {
        this.kind = kind;
        this.spelling = spelling;
        this.row = row;
        this.col = col;
    }

    public final static byte BECOMES = 0, // :=
            TRUE = 1,
            FALSE = 2,
            BEGIN = 3,
            END = 4,
            IF = 5,
            THEN = 6,
            ELSE = 7,
            VAR = 8,
            COLON = 9, // :
            SEMICOLON = 10, // ;
            LPAREN =11, // (
            RPAREN = 12, // )
            BOOLLIT = 13,
            FLOATLIT = 14,
            INTLIT = 15,
            IDENTIFIER = 16,
            WHILE = 17,
            DO = 18,
            COMMA = 19, // ,
            ADD = 20, // +
            MINUS = 21, // -
            OR = 22, // OR
            MULT = 23, // *
            DIV = 24, // /
            AND = 25, // and
            LT = 26, // <
            GT = 27, // >
            LTEQ = 28, // <=
            GTEQ = 29, // >=
            EQ = 30, // =
            DIFF = 31, // <>
            PROGRAM = 32,
            DOT = 33, // .
            LBRACKET = 34, // [
            RBRACKET = 35, // ]
            ARRAY = 36,
            DDOT = 37, // ..
            OF = 38,
            INTEGER = 39,
            REAL = 40,
            BOOL = 41,
            EOT = 42;

    public final static String[] spellings = {
        "BECOMES",
        "TRUE",
        "FALSE",
        "BEGIN",
        "END",
        "IF",
        "THEN",
        "ELSE",
        "VAR",
        "COLON",
        "SEMICOLON",
        "LPAREN",
        "RPAREN",
        "BOOLLIT",
        "FLOATLIT",
        "INTLIT",
        "IDENTIFIER",
        "WHILE",
        "DO",
        "COMMA",
        "ADD",
        "MINUS",
        "OR",
        "MULT",
        "DIV",
        "AND",
        "LT",
        "GT",
        "LTEQ",
        "GTEQ",
        "EQ",
        "DIFF",
        "PROGRAM",
        "DOT",
        "LBRACKET",
        "RBRACKET",
        "ARRAY",
        "DDOT",
        "OF",
        "INTEGER",
        "REAL",
        "BOOL",
        "EOT",
    };

    @Override
    public String toString() {
        return "Token{" + " kind=" + spellings[kind] + ", \tspelling=" + spelling + ", \trow=" + row + ", col=" + col + '}';
    }

}