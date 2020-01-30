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
import AST.*;

/**
 * @author adolfo
 * @author jorgec
 */
public class Parser {

    private Token currentToken;

    public void errorReporter(String expected_token) {
        Error.syntatic(currentToken.row, currentToken.col, new StringBuffer(expected_token), new StringBuffer(currentToken.spelling));
        Error.printErrors();
        System.exit(0);
    }

    private Token accept(byte expectedKind) throws IOException {
        if (currentToken.kind == expectedKind) {
            Token currentTokenAux = currentToken;
            currentToken = Scanner.getToken();
            return new Token(currentTokenAux);
        }
        errorReporter(Token.spellings[expectedKind]);
        return null;
    }

    private Token acceptIt() throws IOException {
        currentToken = Scanner.getToken();
        return new Token(currentToken);
    }

    private AssignmentCommand parseAssignment() throws IOException {
        Variable nodeV = null;
        Expression nodeE = null;

        nodeV = parseVariable();
        accept(Token.BECOMES);
        nodeE = parseExpression();

        return new AssignmentCommand(nodeV, nodeE);
    }

    private Variable parseVariable() throws IOException {
        Token nodeT = null;
        Selector nodeS = null;

        nodeT = accept(Token.IDENTIFIER);
        nodeS = parseSelector();

        return new Variable(nodeT, nodeS);
    }

    private Selector parseSelector() throws IOException {
        Selector first = null;
        Selector current = null;
        Selector last = null;
        Expression nodeE = null;

        while (currentToken.kind == Token.LBRACKET) {
            acceptIt();
            nodeE = parseExpression();
            accept(Token.RBRACKET);

            current = new Selector(nodeE, null);

            if (first == null) {
                first = current;
                last = current;
                continue;
            }
            last.nextS = current;
            last = current;
        }
        return first;
    }

    private Expression parseExpression() throws IOException {
        SimpleExpression nodeSimpleE1 = null;
        SimpleExpression nodeSimpleE2 = null;
        Token T = null;

        nodeSimpleE1 = parseSimpleExpression();

        switch (currentToken.kind) {
            case Token.LT:
            case Token.GT:
            case Token.LTEQ:
            case Token.GTEQ:
            case Token.EQ:
            case Token.DIFF:
                T = acceptIt();
                nodeSimpleE2 = parseSimpleExpression();
        }
        
        

        return new Expression(nodeSimpleE1, new Operator(T), nodeSimpleE2);

    }

    private SimpleExpression parseSimpleExpression() throws IOException {
        Term nodeT = null;
        Term tseq = null;
        TermsSequence first = null;
        TermsSequence current = null;
        TermsSequence last = null;
        Operator nodeO = null;

        nodeT = parseTerm();

        while (currentToken.kind == Token.ADD || currentToken.kind == Token.MINUS
                || currentToken.kind == Token.OR) {
            nodeO = new Operator(acceptIt());

            tseq = parseTerm();

            current = new TermsSequence(nodeO, tseq, null);

            if (first == null) {
                first = current;
                last = current;
                continue;
            }

            last.nextTS = current;
            last = current;
        }

        return new SimpleExpression(nodeT, first);
    }

    private Term parseTerm() throws IOException {
        FactorsSequence first = null;
        FactorsSequence current = null;
        FactorsSequence last = null;
        Factor nodeF = null;
        Factor fseq = null;
        Operator nodeO = null;

        nodeF = parseFactor();

        while (currentToken.kind == Token.MULT || currentToken.kind == Token.DIV
                || currentToken.kind == Token.AND) {
            nodeO = new Operator(acceptIt());
            fseq = parseFactor();

            current = new FactorsSequence(nodeO, fseq, null);

            if (first == null) {
                first = current;
                last = current;
                continue;
            }
            last.nextFS = current;
            last = current;
        }

        return new Term(nodeF, first);
    }

    private Factor parseFactor() throws IOException {
        Factor nodeF = null;

        switch (currentToken.kind) {
            case Token.IDENTIFIER:
                nodeF = parseVariable();
                break;
            case Token.TRUE:
            case Token.FALSE:
            case Token.INTLIT:
            case Token.FLOATLIT:
                nodeF = new Literal(acceptIt());
                break;
            case Token.LPAREN:
                acceptIt();
                nodeF = parseExpression();
                accept(Token.RPAREN);
                break;
            default:
                errorReporter("VARIABLE, LITERAL ou (");
        }

        return nodeF;
    }

    private Token parseId() throws IOException {
        if (currentToken.kind == Token.IDENTIFIER) {
            return acceptIt();
        }
        errorReporter("IDENTIFIER");
        return null;
    }

    private Command parseCompostCommand() throws IOException {
        CommandsList nodeCommandL = null;

        accept(Token.BEGIN);
        nodeCommandL = parseCommandsList();
        accept(Token.END);

        return new CompostCommand(nodeCommandL);
    }

    private CommandsList parseCommandsList() throws IOException {
        CommandsList firstElement = null;
        CommandsList lastElement = null;
        CommandsList currentElement = null;

        while (currentToken.kind == Token.IDENTIFIER || currentToken.kind == Token.IF
                || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN) {

            currentElement = new CommandsList(parseCommand(), null);

            accept(Token.SEMICOLON);

            if (firstElement == null) {
                firstElement = currentElement;
                lastElement = currentElement;
                continue;
            }

            lastElement.nextCL = currentElement;

            lastElement = currentElement;

        }

        return firstElement;
    }

    private Command parseCommand() throws IOException {
        Command nodeC = null;

        switch (currentToken.kind) {
            case Token.IDENTIFIER:
                nodeC = parseAssignment();
                break;
            case Token.IF:
                nodeC = parseConditional();
                break;
            case Token.WHILE:
                nodeC = parseIterative();
                break;
            case Token.BEGIN:
                nodeC = parseCompostCommand();
                break;
            default:
                errorReporter("IDENTIFIER, IF, WHILE ou BEGIN");
        }
        return nodeC;
    }

    private Command parseConditional() throws IOException {
        Expression nodeE = null;
        Command nodeC1 = null;
        Command nodeC2 = null;

        accept(Token.IF);
        nodeE = parseExpression();
        accept(Token.THEN);
        nodeC1 = parseCommand();

        if (currentToken.kind == Token.ELSE) {
            acceptIt();
            nodeC2 = parseCommand();
        }

        return new ConditionalCommand(nodeE, nodeC1, nodeC2);
    }

    private Command parseIterative() throws IOException {
        Command nodeC = null;
        Expression nodeE = null;

        accept(Token.WHILE);
        nodeE = parseExpression();
        accept(Token.DO);
        nodeC = parseCommand();

        return new IterativeCommand(nodeE, nodeC);
    }

    private AggregateType parseAggregateType() throws IOException {
        Type nodeT = null;
        Token INDEX1 = null;
        Token INDEX2 = null;
        
        acceptIt();
        accept(Token.LBRACKET);
        INDEX1 = accept(Token.INTLIT);
        accept(Token.DDOT);
        INDEX2 = accept(Token.INTLIT);
        accept(Token.RBRACKET);
        accept(Token.OF);
        nodeT = parseType();
        
        return new AggregateType(INDEX1, INDEX2, nodeT);
    }

    private Type parseType() throws IOException {
        Type nodeT = null;

        switch (currentToken.kind) {
            case Token.ARRAY:
                acceptIt();
                nodeT = parseAggregateType();
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOL:
                nodeT = new SimpleType(acceptIt());
                break;
            default:
                errorReporter("ARRAY, INTEGER, REAL ou BOOLEAN");
        }
        
        return nodeT;
    }

    private IdsList parseIdsList() throws IOException {
        IdsList first = null;
        IdsList current = null;
        IdsList last = null;
        Token id = null;

        id = accept(Token.IDENTIFIER);
        first = new IdsList(id);
        last = first;

        while (currentToken.kind == Token.COMMA) {
            acceptIt();
            id = accept(Token.IDENTIFIER);
            last.nextIL = new IdsList(id);
            last = last.nextIL;
        }

        return first;
    }

    private Declaration parseDeclarations() throws IOException {
        Declaration first = null;
        Declaration current = null;
        Declaration last = null;

        while (currentToken.kind == Token.VAR) {
            current = parseDeclaration();

            if (first == null) {
                first = current;
                last = current;
                continue;
            }
            last.nextD = current;
            last = current;
        }

        return first;
    }

    private Declaration parseDeclaration() throws IOException {
        Declaration nodeD = null;
        IdsList nodeIdL = null;
        Type nodeT = null;

        acceptIt();
        nodeIdL = parseIdsList();
        accept(Token.COLON);
        nodeT = parseType();

        return new VariableDeclaration(nodeIdL, nodeT);
    }

    private Body parseBody() throws IOException {
        Declaration nodeD = null;
        Command nodeC = null;

        if (currentToken.kind == Token.VAR) {
            nodeD = parseDeclarations();
        }
//          if (currentToken.kind == Token.BEGIN) {
//            parseCompostCommand();
//        }
        if (currentToken.kind == Token.BEGIN) {
            nodeC = parseCompostCommand();
            return new Body(nodeD, nodeC);
        }
        errorReporter("BEGIN ou VAR");
        return null;
    }

    private Program parseProgram() throws IOException {
        Token nodeId = null;
        Body nodeB = null;

        accept(Token.PROGRAM);
        nodeId = parseId();
        accept(Token.SEMICOLON);
        nodeB = parseBody();
        accept(Token.DOT);

        return new Program(nodeId, nodeB);
    }

    public Program parse() throws IOException {
        Program nodeP = null;
        currentToken = Scanner.getToken();
        nodeP = parseProgram();
        if (currentToken.kind != Token.EOT) {
            errorReporter("EOT");
        }
        return nodeP;
    }
}
