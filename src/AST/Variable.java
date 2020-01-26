/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import compiler_.Token;
import syntaxAnalisys.Visitor;

/**
 *
 * @author jorgec
 */
public class Variable extends Factor {
    public Token T;
    public Selector S;
    public Node declaration;
    
    public void visit(Visitor v){
        v.visitVariable(this);
    }
    
    public Variable(Token T, Selector S){
        this.T = T;
        this.S = S;
    }
}
