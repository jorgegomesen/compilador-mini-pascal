/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

import java.io.IOException;
import lexicalAnalisys.Token;
import lexicalAnalisys.Scanner;
import errorHandling.Error;


/**
 * @author adolfo
 * @author jorgec
 */
public class Parser {

    private Token currentToken;
//    private Scanner scanner;

    public Parser(Scanner scanner) throws IOException {
//        this.scanner = scanner;
    }

    public void errorReporter(String expected_token) {
        Error.syntatic(currentToken.row, currentToken.col, new StringBuffer(expected_token), new StringBuffer(currentToken.spelling));
        Error.printErrors();
        System.exit(0);
    }

    private void accept(byte expectedKind) throws IOException {
        if (currentToken.kind == expectedKind) {
            currentToken = Scanner.getToken();
            return;
        }
        errorReporter(Token.spellings[expectedKind]);
    }

    private void acceptIt() throws IOException {
        currentToken = Scanner.getToken();
    }

    private void parseAssignment() throws IOException {
//        System.out.println("\n# Assignment #");
        parseVariable();
        accept(Token.BECOMES);
        parseExpression();
//        System.out.println("# saiu do Assignment #\n");
    }

    private void parseVariable() throws IOException {
//        System.out.println("\n# Variable #");
        accept(Token.IDENTIFIER);
        parseSelector();
//        System.out.println("# saiu do Variable #\n");
    }

    private void parseSelector() throws IOException {
//        System.out.println("\n# Selector #");
        while (currentToken.kind == Token.LBRACKET) {
            acceptIt();
            parseExpression();
            accept(Token.RBRACKET);
        }
//        System.out.println("# saiu do Selector #\n");
    }

    private void parseExpression() throws IOException {
//        System.out.println("\n# Expression #");
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
//        System.out.println("# saiu do Expression #\n");
    }

    private void parseSimpleExpression() throws IOException {
//        System.out.println("\n# SimpleExpression #");
        parseTerm();
        while (currentToken.kind == Token.ADD || currentToken.kind == Token.MINUS
                || currentToken.kind == Token.OR) {
            acceptIt();
            parseTerm();
        }
//        System.out.println("# saiu do SimpleExpression #\n");
    }

    private void parseTerm() throws IOException {
//        System.out.println("\n# Term #");
        parseFactor();
        while (currentToken.kind == Token.MULT || currentToken.kind == Token.DIV
                || currentToken.kind == Token.AND) {
            acceptIt();
            parseFactor();
        }
//        System.out.println("# saiu do Term #\n");
    }

    private void parseFactor() throws IOException {
//        System.out.println("\n# Factor #");
//        System.out.println("Token ==> " + currentToken.spelling + " - " + Token.spellings[currentToken.kind]);
        switch (currentToken.kind) {
            case Token.IDENTIFIER:
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
//        System.out.println("# saiu do Factor #\n");
    }

    private void parseId() throws IOException {
//        System.out.println("\n# Id #");
        if (currentToken.kind == Token.IDENTIFIER) {
            acceptIt();
//        System.out.println("# saiu do Id #\n");
            return;
        }
        errorReporter("IDENTIFIER");
//        System.out.println("# saiu do Id #\n");
    }

    private void parseCompostCommand() throws IOException {
//        System.out.println("\n# CompostCommand #");
        accept(Token.BEGIN);
        parseCommandsList();
        accept(Token.END);
//        System.out.println("# saiu do CompostCommand #\n");
    }

    private void parseCommandsList() throws IOException {
//        System.out.println("\n# CommandsList #");
        while (currentToken.kind == Token.IDENTIFIER || currentToken.kind == Token.IF
                || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN) {

            parseCommand();
            accept(Token.SEMICOLON);
        }
//        System.out.println("# saiu do CommandsList #\n");
    }

    private void parseCommand() throws IOException {
//        System.out.println("\n# Command #");
        switch (currentToken.kind) {
            case Token.IDENTIFIER:
//                acceptIt();
                parseAssignment();
                break;
            case Token.IF:
//                acceptIt();
                parseConditional();
                break;
            case Token.WHILE:
//                acceptIt();
                parseIterative();
                break;
            case Token.BEGIN:
//                acceptIt();
                parseCompostCommand();
                break;
            default:
                errorReporter("IDENTIFIER, IF, WHILE ou BEGIN");
        }
//        System.out.println("# saiu do Command #\n");
    }

    private void parseConditional() throws IOException {
//        System.out.println("\n# Conditional #");
        accept(Token.IF);
        parseExpression();
        accept(Token.THEN);
        parseCommand();
        if (currentToken.kind == Token.ELSE) {
            acceptIt();
            parseCommand();
        }
//        System.out.println("# saiu do Conditional #\n");
    }

    private void parseIterative() throws IOException {
//        System.out.println("\n# Iterative #");
        accept(Token.WHILE);
        parseExpression();
        accept(Token.DO);
        parseCommand();
//        System.out.println("# saiu do Iterative #\n");
    }

    private void parseAggregateType() throws IOException {
//        System.out.println("\n# AggregateType #");
        acceptIt();
        accept(Token.LBRACKET);
        accept(Token.INTLIT);
        accept(Token.DDOT);
        accept(Token.INTLIT);
        accept(Token.RBRACKET);
        accept(Token.OF);
        /* Revisar o tipo assumido pelo array */
        parseType();
//        System.out.println("# saiu do AggregateType #\n");
    }

    private void parseType() throws IOException {
//        System.out.println("\n# ParseType #");
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
                errorReporter("ARRAY, INTEGER, REAL ou BOOLEAN");
        }
//        System.out.println("# saiu do Type #\n");
    }

    private void parseIdsList() throws IOException {
//        System.out.println("\n# IdsList #");
        accept(Token.IDENTIFIER);

        while (currentToken.kind == Token.COMMA) {
            acceptIt();
            accept(Token.IDENTIFIER);
        }
//        System.out.println("# saiu do IdsList #\n");
    }

    private void parseDeclarations() throws IOException {
//        System.out.println("\n# Declarations #");
        while (currentToken.kind == Token.VAR) {
            parseDeclaration();
        }
        if (currentToken.kind == Token.BEGIN) {
            parseCompostCommand();
        }
//        System.out.println("# saiu do Declarations #\n");
    }

    private void parseDeclaration() throws IOException {
//        System.out.println("\n# Declaration #");
        acceptIt();
        parseIdsList();
        accept(Token.COLON);
        parseType();
//        System.out.println("# saiu do Declaration #\n");
    }

    private void parseBody() throws IOException {
//        System.out.println("# Body #");
        if (currentToken.kind == Token.VAR) {
            parseDeclarations();
        }
        if (currentToken.kind == Token.BEGIN) {
            parseCompostCommand();
            return;
        }
        errorReporter("BEGIN ou VAR");
    }

    private void parseProgram() throws IOException {
//        System.out.println("# Program #");
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseBody();
        accept(Token.DOT);
    }

    public void parse() throws IOException {
        currentToken = Scanner.getToken();
        parseProgram();
        if (currentToken.kind != Token.EOT) {
            errorReporter("EOT");
        }
    }
}
