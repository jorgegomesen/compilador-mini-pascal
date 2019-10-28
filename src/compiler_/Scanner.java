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
public class Scanner {

    private char currentChar; // first source character
    private byte currentKind;
    private StringBuffer currentSpelling;

//    ambos take e takeit buscam o próximo caractere da fonte e armazenam em currentChar
    private void take(char expectedChar) {
        if (currentChar == expectedChar) {
            currentSpelling.append(currentChar);
//            currentChar = next source character;
            return;
        }
//        report a lexical error
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
//        currentChar = next source character;
    }

    private boolean isDigit(char c) {
//        digit ::= 0|1|...|9
        return (c > 47 && c < 58);
    }

    private boolean isLetter(char c) {
//        letter ::= a|b|c|...|z
        return (c > 96 && c < 123);
    }

    private boolean isGraphic(char c) {
        return (c != -1 && c != 10);
    }

    private byte scanToken() {
//        palavras reservadas e identificadores
        if (isLetter(currentChar)) {
            takeIt();
            while (isLetter(currentChar) || isDigit(currentChar)) {
                takeIt();
            }
            switch (currentSpelling.toString()) {
                case "or":
                    return Token.OR;
                case "and":
                    return Token.AND;
                case "do":
                    return Token.DO;
                case "while":
                    return Token.WHILE;
                case "true":
                    return Token.TRUE;
                case "false":
                    return Token.FALSE;
                case "begin":
                    return Token.BEGIN;
                case "end":
                    return Token.END;
                case "if":
                    return Token.IF;
                case "then":
                    return Token.THEN;
                case "else":
                    return Token.ELSE;
                case "var":
                    return Token.VAR;
                case "program":
                    return Token.PROGRAM;
                case "array":
                    return Token.ARRAY;
                case "of":
                    return Token.OF;
                case "integer":
                    return Token.INTEGER;
                case "real":
                    return Token.REAL;
                case "boolean":
                    return Token.BOOL;
            }
            return Token.IDENTIFIER;
        }
//        INTLIT ou FLOATLIT
        if (isDigit(currentChar) || currentChar == '.') {
            boolean is_float = currentChar == '.';
            takeIt();
            while (isDigit(currentChar) || (is_float = (currentChar == '.'))) {
                takeIt();
                if (isLetter(currentChar)) {
//                    invalid intlit or floatlit
//                    generate an error
                }
            }
            if (is_float) {
                return Token.FLOATLIT;
            }
            return Token.INTLIT;
        }
        switch (currentChar) {
            case '+':
                takeIt();
                return Token.ADD;
            case '-':
                takeIt();
                return Token.MINUS;
            case '*':
                takeIt();
                return Token.MULT;
            case '/':
                takeIt();
                return Token.DIV;
            case '<':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.LTEQ;
                }
                if (currentChar == '>') {
                    takeIt();
                    return Token.DIFF;
                }
                return Token.LT;
            case '>':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.GTEQ;
                }
                takeIt();
                return Token.GT;
            case '=':
                takeIt();
                return Token.EQ;
            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.BECOMES;
                }
                return Token.COLON;
            case ';':
                takeIt();
                return Token.SEMICOLON;
            case '(':
                takeIt();
                return Token.LPAREN;
            case ')':
                takeIt();
                return Token.RPAREN;
            case '[':
                takeIt();
                return Token.LBRACKET;
            case ']':
                takeIt();
                return Token.RBRACKET;
            case '\000':
                return Token.EOT;
            default:
//                    generate an error
                return 0;
        }
    }

    private void scanSeparator() {
        switch (currentChar) {
            case '!':
                takeIt();
                while (isGraphic(currentChar)) {
                    takeIt();
                }
                take('\n');
                break;
            case ' ':
            case '\n':
                takeIt();
        }
    }

    public Token scan() {
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\n') {
            scanSeparator();
        }
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
//        como pegar a posição do caractere?
        return new Token(currentKind, currentSpelling.toString(), 0, 0);
    }

}
