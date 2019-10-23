/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            LPAREN = 11, // (
            RPAREN = 12, // )
            FLOATLIT = 13,
            INTLIT = 14,
            IDENTIFIER = 15,
            WHILE = 16,
            DO = 17,
            COMMA = 18, // ,
            ADD = 19, // +
            MINUS = 20, // -
            OR = 21,
            MULT = 22, // *
            DIV = 23, // \
            AND = 24,
            LT = 25, // <
            GT = 26, // >
            LTEQ = 27, // <=
            GTEQ = 28, // >=
            EQ = 29, // =
            DIFF = 30, // <>
            GRAPHIC = 31, // !, @, #, ...
            PROGRAM = 32,
            DOT = 33, // .
            LBRACKET = 34, // [
            RBRACKET = 35, // ]
            ARRAY = 36,
            DDOT = 37, // ..
            OF = 38,
            INTEGER = 39,
            REAL = 40,
            BOOL = 41;
}
