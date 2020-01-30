/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalAnalisys;

import syntaxAnalisys.Visitor;

/**
 * @author adolfo
 * @author jorgec
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
    
    public Token(Token token){
        this.kind = token.kind;
        this.spelling = token.spelling;
        this.row = token.row;
        this.col = token.col;
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
            //            BOOLLIT = 13, //
            INTLIT = 13, //
            IDENTIFIER = 14, //
            WHILE = 15, //
            DO = 16, //
            COMMA = 17, // ,
            ADD = 18, // +
            MINUS = 19, // -
            OR = 20, // OR
            MULT = 21, // *
            DIV = 22, // /
            AND = 23, // and
            LT = 24, // <
            GT = 25, // >
            LTEQ = 26, // <=
            GTEQ = 27, // >=
            EQ = 28, // =
            DIFF = 29, // <>
            PROGRAM = 30, //
            DOT = 31, // .
            LBRACKET = 32, // [
            RBRACKET = 33, // ]
            ARRAY = 34, //
            DDOT = 35, // ..
            OF = 36, //
            INTEGER = 37, // 
            REAL = 38, //
            BOOL = 39, //
            SEPARATOR = 40,
            EOT = 41, // Fim do arquivo
            FLOATLIT = 42;   //

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
        //        "BOOLLIT",
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

    public void visit(Visitor v) {
        v.visitToken(this);
    }

    @Override
    public String toString() {
        String linha = String.format("%-6s%-7s%-11s%-12s%-13s%-7s%-3s%-3s%-3s%-2s", "Token{",
                " kind: ", spellings[kind], ", spelling: ", spelling, ", row= ", row, ", col= ", col, "}");

        return linha;

    }

}
