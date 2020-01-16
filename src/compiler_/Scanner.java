/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author unknown
 */
public class Scanner {

    private char current_char;              // first source character
    private byte current_kind;              // tipo atual
    private int current_row;                // linha atual
    private int current_col;                // coluna atual
    private StringBuffer current_spelling;  // grafia ou valor atual
    private final BufferedReader bufferRead;// 

    public Scanner(BufferedReader bufferRead) throws IOException {
        this.current_row = 1;
        this.current_col = 1;
        this.bufferRead = bufferRead;
        this.current_char = getNextCharacter();
        this.current_spelling = new StringBuffer("");
    }

    private char getNextCharacter() throws IOException {
        char next_character;
        if (bufferRead.ready()) {
            next_character = (char) bufferRead.read();
            if (current_char == '\n') {
                current_row++;
                this.current_col = 1;
            }
            return next_character;
        }
        return '\000';
    }

//    ambos take e takeit buscam o próximo caractere da fonte e armazenam em current_char
    private void take(char expectedChar) throws IOException {
        if (current_char != '\n') {
            current_col++;
        }
        if (current_char == expectedChar) {
            current_spelling.append(current_char);
            current_char = getNextCharacter();
            return;
        }
        Error.lexical(current_row, current_col, current_spelling.append(expectedChar),
                current_spelling.append(current_char));
    }

    private void takeIt() throws IOException {
        if (current_char != '\n') // Se não encontrar o final da linha incrementa col
        {
            current_col++;
        }

        current_spelling.append(current_char);

        current_char = getNextCharacter();
    }

    private boolean isSeparator(char c) {
        return (c == ' ' || c == '\t' || c == '\r' || c == '\n');
    }

    private boolean isDigit(char c) {
//        digit ::= 0|1|...|9
        return (c > 47 && c < 58);
    }

    private boolean isLetter(char c) {
//        letter ::= a|b|c|...|z
        return (c > 96 && c < 123) || (c > 64 && c < 91);
    }

    private boolean isGraphic(char c) {
        return (c != -1 && c != 10);
    }

    private byte scanToken() throws IOException {
//        palavras reservadas e identificadores
        if (isLetter(current_char)) {
            takeIt();
            while (isLetter(current_char) || isDigit(current_char)) {
                takeIt();
            }
            switch (current_spelling.toString()) {
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
//        INTLIT
        if (isDigit(current_char)) {
            boolean is_float = false;

            while (isDigit(current_char) || current_char == '.') {
                takeIt();
                if (current_char == '.') {
                    if (is_float) {
                        int current_col_aux = current_col;
                        while (!isSeparator(current_char)) {
                            takeIt();
                        }
                        Error.lexical(current_row, current_col_aux, new StringBuffer("Float válido"),
                                new StringBuffer(current_spelling + ""));
                        return -1;
                    }
                    is_float = true;
                }
            }

            if (!isDigit(current_char) && current_char != ' ' && current_char != '\n') {
                int current_col_aux = current_col;
                while (!isSeparator(current_char)) {
                    takeIt();
                }
                Error.lexical(current_row, current_col_aux, new StringBuffer("Nùmero válido"),
                        new StringBuffer(current_spelling + ""));
                return -1;
            }

            if (is_float) {
                return Token.FLOATLIT;
            }

            return Token.INTLIT;
        }
        switch (current_char) {
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
                if (current_char == '=') {
                    takeIt();
                    return Token.LTEQ;
                }
                if (current_char == '>') {
                    takeIt();
                    return Token.DIFF;
                }
                return Token.LT;
            case '>':
                takeIt();
                if (current_char == '=') {
                    takeIt();
                    return Token.GTEQ;
                }
                return Token.GT;
            case '=':
                takeIt();
                return Token.EQ;
            case ':':
                takeIt();
                if (current_char == '=') {
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
            case '\n':
            case '.': {
                takeIt();
                boolean is_float = isDigit(current_char);

                while (isDigit(current_char)) {
                    takeIt();
                }

                if (!isDigit(current_char) && current_char != ' ' && current_char != '\n') {
                    int current_col_aux = current_col;
                    while (!isSeparator(current_char)) {
                        takeIt();
                    }
                    Error.lexical(current_row, current_col_aux, new StringBuffer("Float válido"),
                            new StringBuffer(current_spelling + ""));
                    return -1;
                }

                if (is_float) {
                    return Token.FLOATLIT;
                }

                if (current_char == '.') {
                    takeIt();
                    return Token.DDOT;
                }
                return Token.DOT;
            }
            case ',':
                takeIt();
                return Token.COMMA;
            default:
                Error.lexical(current_row, current_col, new StringBuffer("símbolo válido"),
                        new StringBuffer(current_spelling + (current_char + "")));
                return -1;
        }
    }

    private void scanSeparator() throws IOException {
        switch (current_char) {
            case '!':
                takeIt();
                while (isGraphic(current_char)) {
                    takeIt();
                }
                take('\n');
                break;
            case ' ':
            case '\n':
            case '\r': // quando chegar no final da linha ele pular para a proxima
                takeIt();
        }
    }

    public Token scan() throws IOException {
        int col;

        while (current_char == '!' || isSeparator(current_char)) {
            scanSeparator();
        }

        col = current_col;
        current_spelling = new StringBuffer("");

        current_kind = scanToken();

        if (current_kind != -1) {
            return new Token(current_kind, current_spelling.toString(), current_row, col);
        }
        
        current_char = getNextCharacter();
        return scan();
    }

}
