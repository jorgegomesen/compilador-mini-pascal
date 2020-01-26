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
public class Literal extends Factor {
    public Token T;
    
    public void visit(Visitor v){
        v.visitLiteral(this);
    }
    
    public Literal(Token T){
        this.T = T;
    }
}
