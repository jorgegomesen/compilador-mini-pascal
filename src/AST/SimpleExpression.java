/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import syntaxAnalisys.Visitor;

/**
 *
 * @author jorgec
 */
public class SimpleExpression extends Node {
    public Term T;
    public TermsSequence TS;
    public int type;
    
    public void visit(Visitor v){
        v.visitSimpleExpression(this);
    }
    
    public SimpleExpression(Term T, TermsSequence TS){
        this.T = T;
        this.TS = TS;
    }
}
