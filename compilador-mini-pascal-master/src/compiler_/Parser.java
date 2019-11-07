/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

import java.io.IOException;

/**
 *
 * @author unknown
 */
public class Parser {

    private Token currentToken;
    private Scanner scanner;

    public void parser(Scanner scanner) throws IOException {
        this.scanner = scanner;
//        currentToken = scanner.scan();
//        parseSentence();
//        check that no terminal follows the sentence
    }

    private void accept(byte expectedKind) throws IOException {
        if (currentToken.kind == expectedKind) {
            currentToken = scanner.scan();
            return;
        }
        Error.syntatic(new StringBuffer(Token.spellings[expectedKind]), new StringBuffer(currentToken.spelling));
    }

    private void acceptIt() throws IOException {
        currentToken = scanner.scan();
    }

    private void parseId() throws IOException {
        if (currentToken.kind == Token.IDENTIFIER) {
            acceptIt();
            return;
        }
        Error.syntatic(new StringBuffer("IDENTIFIER"), new StringBuffer(currentToken.spelling));
    }

    private void parseCompostCommand() {

    }

    private void parseAggregateType(){
        
    }
    
    private void parseType() throws IOException {
        switch(currentToken.kind){
            case Token.ARRAY:
                acceptIt();
                accept(Token.LBRACKET);
//                accept(Token.INTLIT);  tem de ser LITERAL
                accept(Token.DDOT);
//                accept(Token.INTLIT);  tem de ser LITERAL
                accept(Token.RBRACKET);
                accept(Token.OF);
                parseType(); /* revisar */
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOL:
                acceptIt();
                break;
            default:
        }
    }

    private void parseDeclaration() throws IOException {
        acceptIt();
        accept(Token.IDENTIFIER);

        while (currentToken.kind == Token.COMMA) {
            acceptIt();
            accept(Token.IDENTIFIER);
        }

        accept(Token.COLON);

        parseType();
    }

    private void parseBody() throws IOException {
        if (currentToken.kind == Token.VAR) {
            parseDeclaration();
        }
        if (currentToken.kind == Token.BEGIN) {
            parseCompostCommand();
            return;
        }
        Error.syntatic(new StringBuffer("BEGIN"), new StringBuffer(currentToken.spelling));
    }

    private void parseProgram() throws IOException {
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseBody();
        accept(Token.DOT);
    }

    private void parse() throws IOException {
        currentToken = scanner.scan();
        parseProgram();
        if (currentToken.kind != Token.EOT) {
            Error.syntatic(new StringBuffer("EOT"), new StringBuffer(currentToken.spelling));
        }
    }

    private void parseSentence() {

    }
}
