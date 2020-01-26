/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

import AST.*;

/**
 *
 * @author jorgec
 */
public interface Visitor {

    public void visitAssignmentCommand(AssignmentCommand AC);
    public void visitCompostCommand(CompostCommand CC);
    public void visitConditionalCommand(ConditionalCommand CC);
    public void visitIterativeCommand(IterativeCommand IC);
    public void visitCommand(Command C);
    public void visitBody(Body B);
    public void visitVariableDeclaration(VariableDeclaration VD);
    public void visitDeclaration(Declaration D);
    public void visitSequentialDeclaration(SequentialDeclaration SD);
    public void visitExpression(Expression E);
    public void visitSimpleExpression(SimpleExpression SE);
    public void visitFactor(Factor F);
//    public void visitParenthesisFactor(ParenthesisFactor PF);
    public void visitCommandsList(CommandsList CL);
    public void visitIdsList(IdsList IL);
    public void visitLiteral(Literal L);
    public void visitNode(Node N);
    public void visitOperator(Operator O);
    public void visitProgram(Program P);
    public void visitSelector(Selector S);
    public void visitFactorsSequence(FactorsSequence SF);
    public void visitTermsSequence(TermsSequence TS);
    public void visitTerm(Term T);
    public void visitAggregateType(AggregateType AT);
    public void visitType(Type T);
    public void visitSimpleType(SimpleType ST);
    public void visitVariable(Variable V);
    public void visitIdentifier(Identifier I);
}
