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

    public byte kind;           // Tipo do token
    public String spelling;     // Valor do token
    public int row;             // Linha que o token está
    public int col;             // Coluna que o token está

    public Token(byte kind, String spelling, int row, int col) {
        this.kind = kind;
        this.spelling = spelling;
        this.row = row;
        this.col = col;
    }

    public final static byte BECOMES = 0, // :=
            TRUE = 1, //
            FALSE = 2, //
            BEGIN = 3, //
            END = 4, //
            IF = 5, //
            THEN = 6, //
            ELSE = 7, //
            VAR = 8, //
            COLON = 9, // :
            SEMICOLON = 10, // ;
            LPAREN = 11, // (
            RPAREN = 12, // )
            BOOLLIT = 13, //
            INTLIT = 14, //
            IDENTIFIER = 15, //
            WHILE = 16, //
            DO = 17, //
            COMMA = 18, // ,
            ADD = 19, // +
            MINUS = 20, // -
            OR = 21, // OR
            MULT = 22, // *
            DIV = 23, // /
            AND = 24, // and
            LT = 25, // <
            GT = 26, // >
            LTEQ = 27, // <=
            GTEQ = 28, // >=
            EQ = 29, // =
            DIFF = 30, // <>
            PROGRAM = 31, //
            DOT = 32, // .
            LBRACKET = 33, // [
            RBRACKET = 34, // ]
            ARRAY = 35, //
            DDOT = 36, // ..
            OF = 37, //
            INTEGER = 38, // 
            REAL = 39, //
            BOOL = 40, //
            SEPARATOR = 41,
            EOT = 42, // Fim do arquivo
            FLOATLIT = 43;   //

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
        "SEPARATOR",
        "EOT",
        "FLOATLIT"
    };

    @Override
    public String toString() {
        String linha = String.format("%-6s%-7s%-11s%-12s%-13s%-7s%-3s%-3s%-3s%-2s", "Token{",
                " kind: ", spellings[kind], ", spelling: ", spelling, ", row= ", row, ", col= ", col, "}");

        return linha;

    }

}
