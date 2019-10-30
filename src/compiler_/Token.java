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
            //            TRUE = 1,
            //            FALSE = 2,
            BEGIN = 1,
            END = 2,
            IF = 3,
            THEN = 4,
            ELSE = 5,
            VAR = 6,
            COLON = 7, // :
            SEMICOLON = 8, // ;
            LPAREN = 9, // (
            RPAREN = 10, // )
            BOOLLIT = 11,
            FLOATLIT = 12,
            INTLIT = 13,
            IDENTIFIER = 14,
            WHILE = 15,
            DO = 16,
            COMMA = 17, // ,
            //            ADD = 18, // +
            //            MINUS = 20, // -
            //            OR = 21,
            //            MULT = 22, // *
            //            DIV = 23, // \
            //            AND = 24,
            OPAD = 18, // + | - | or
            OPMUL = 19, // * | / | and
            LT = 20, // <
            GT = 21, // >
            LTEQ = 22, // <=
            GTEQ = 23, // >=
            EQ = 24, // =
            DIFF = 25, // <>
            GRAPHIC = 26, // !, @, #, ...
            PROGRAM = 27,
            DOT = 28, // .
            LBRACKET = 29, // [
            RBRACKET = 30, // ]
            ARRAY = 31,
            DDOT = 32, // ..
            OF = 33,
            INTEGER = 34,
            REAL = 35,
            BOOL = 36,
            EOT = 37;

    public final static String[] spellings = {
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
        "OPAD",
        "OPMUL",
        "LT",
        "GT",
        "LTEQ",
        "GTEQ",
        "EQ",
        "DIFF",
        "GRAPHIC",
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
        "EOT"
    };

    @Override
    public String toString() {
        return "Token{" + " kind=" + spellings[kind-1] + ", spelling=" + spelling + ", row=" + row + ", col=" + col + '}';
    }

}
