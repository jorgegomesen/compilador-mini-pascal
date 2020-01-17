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

    public Parser(Scanner scanner) throws IOException {
        this.scanner = scanner;
    }
    
    public void errorReporter(String expected_token) {
        Error.syntatic(new StringBuffer(expected_token), new StringBuffer(currentToken.spelling));
    }

    private void accept(byte expectedKind) throws IOException {
        if (currentToken.kind == expectedKind) {
            currentToken = scanner.scan();
            return;
        }
        errorReporter(Token.spellings[expectedKind]);
    }

    private void acceptIt() throws IOException {
        currentToken = scanner.scan();
    }

    private void parseAssignment() throws IOException {
        parseVariable();
        accept(Token.BECOMES);
        parseExpression();
    }

    private void parseVariable() throws IOException {
        accept(Token.IDENTIFIER);
        parseSelector();
    }

    private void parseSelector() throws IOException {
        while (currentToken.kind == Token.LBRACKET) {
            acceptIt();
            parseExpression();
            accept(Token.RBRACKET);
        }
    }

    private void parseExpression() throws IOException {
        parseSimpleExpression();
        switch (currentToken.kind) {
            case Token.LT:
            case Token.GT:
            case Token.LTEQ:
            case Token.GTEQ:
            case Token.EQ:
            case Token.DIFF:
                acceptIt();
                parseSimpleExpression();
        }
    }

    private void parseSimpleExpression() throws IOException {
        parseTerm();
        while (currentToken.kind == Token.ADD || currentToken.kind == Token.MINUS
                || currentToken.kind == Token.OR) {
            acceptIt();
            parseTerm();
        }
    }

    private void parseTerm() throws IOException {
        parseFactor();
        while (currentToken.kind == Token.MULT || currentToken.kind == Token.DIV
                || currentToken.kind == Token.AND) {
            acceptIt();
            parseFactor();
        }
    }

    private void parseFactor() throws IOException {
        switch (currentToken.kind) {
            case Token.IDENTIFIER:
                acceptIt();
                parseVariable();
                break;
            case Token.TRUE:
            case Token.FALSE:
            case Token.INTLIT:
            case Token.FLOATLIT:
                acceptIt();
                break;
            case Token.LPAREN:
                acceptIt();
                parseExpression();
                accept(Token.RPAREN);
                break;
            default:
                errorReporter("VARIABLE, LITERAL ou (");
        }
    }

    private void parseId() throws IOException {
        if (currentToken.kind == Token.IDENTIFIER) {
            acceptIt();
            return;
        }
        errorReporter("IDENTIFIER");
    }

    private void parseCompostCommand() throws IOException {
        accept(Token.BEGIN);
        parseCommandsList();
        accept(Token.END);

    }

    private void parseCommandsList() throws IOException {
        while (currentToken.kind == Token.IDENTIFIER || currentToken.kind == Token.IF
                || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN) {
            parseCommand();
            accept(Token.SEMICOLON);
        }
    }

    private void parseCommand() throws IOException {
        switch (currentToken.kind) {
            case Token.IDENTIFIER:
                acceptIt();
                parseAssignment();
                break;
            case Token.IF:
                acceptIt();
                parseConditional();
                break;
            case Token.WHILE:
                acceptIt();
                parseIterative();
                break;
            case Token.BEGIN:
                acceptIt();
                parseCompostCommand();
                break;
            default:
                errorReporter("IDENTIFIER, IF, WHILE ou BEGIN");
        }
    }

    private void parseConditional() throws IOException {
        accept(Token.IF);
        parseExpression();
        accept(Token.THEN);
        parseCommand();
        if (currentToken.kind == Token.ELSE) {
            acceptIt();
            parseCommand();
        }
    }

    private void parseIterative() throws IOException {
        accept(Token.WHILE);
        parseExpression();
        accept(Token.DO);
        parseCommand();
    }

    private void parseAggregateType() throws IOException {
        acceptIt();
        accept(Token.LBRACKET);
        accept(Token.INTLIT);
        accept(Token.DDOT);
        accept(Token.INTLIT);
        accept(Token.RBRACKET);
        accept(Token.OF);
        /* Revisar o tipo assumido pelo array */
        parseType();
    }

    private void parseType() throws IOException {
        switch (currentToken.kind) {
            case Token.ARRAY:
                acceptIt();
                parseAggregateType();
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOL:
                acceptIt();
                break;
            default:
                errorReporter("ARRAY, INTEGER, REAL ou BOOL");
        }
    }

    private void parseIdsList() throws IOException {
        accept(Token.IDENTIFIER);

        while (currentToken.kind == Token.COMMA) {
            acceptIt();
            accept(Token.IDENTIFIER);
        }
    }

    private void parseDeclarations() throws IOException {
        while (currentToken.kind == Token.VAR) {
            parseDeclaration();
        }
        if (currentToken.kind == Token.BEGIN) {
            parseCompostCommand();
        }
    }

    private void parseDeclaration() throws IOException {
        acceptIt();
        parseIdsList();
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
        errorReporter("BEGIN");
    }

    private void parseProgram() throws IOException {
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseBody();
        accept(Token.DOT);
    }

    public void parse() throws IOException {
        currentToken = scanner.scan();
        parseProgram();
        if (currentToken.kind != Token.EOT) {
            errorReporter("EOT");
        }
    }
}
