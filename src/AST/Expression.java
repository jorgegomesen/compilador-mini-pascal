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
public class Expression extends Factor{
    public SimpleExpression SE1;
    public Operator O;
    public SimpleExpression SE2;
    
    public void visit(Visitor v){
        v.visitExpression(this);
    }
    
    public Expression(SimpleExpression SE1, Operator O, SimpleExpression SE2){
        this.SE1 = SE1;
        this.O = O;
        this.SE2 = SE2;
    }
}
