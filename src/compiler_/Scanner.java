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
//        graphic ::= ! | @ | # | ...
//        o que fazer com estes três pontos? o prof. quis dizer apenas que 
//        poderiam haver mais, não?
        return (c == 33 || c == 35 || c == 64);
    }

    private byte scanToken() {
        if (isLetter(currentChar)) {
            takeIt();
            while (isLetter(currentChar) || isDigit(currentChar)) {
                takeIt();
            }
            return Token.IDENTIFIER;
        }
        if (isDigit(currentChar)) {
            takeIt();
            while (isDigit(currentChar)) {
                takeIt();
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
                if(currentChar == '='){
                    takeIt();
                    return Token.LTEQ;
                }
                if(currentChar == '>'){
                    takeIt();
                    return Token.DIFF;
                }
                return Token.LT;
            case '>':
                takeIt();
                if(currentChar == '='){
                    takeIt();
                    return Token.GTEQ;
                }
                takeIt();
                    return Token.GT;
            case '=':
                takeIt();
                return Token.EQ;
            default:
                return 0;
        }
    }

    private byte scanSeparator() {
        //       As above
        return 1;
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
