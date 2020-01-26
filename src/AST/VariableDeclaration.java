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
public class VariableDeclaration extends Declaration {
    public IdsList IL;
    public Type T;
    
    public void visit(Visitor v){
        v.visitVariableDeclaration(this);
    }
    
    public VariableDeclaration(IdsList IL, Type T, Declaration nextD){
        super(nextD);
        this.IL = IL;
        this.T = T;
    }
    
    public VariableDeclaration(IdsList IL, Type T){
        super(null);
        this.IL = IL;
        this.T = T;
    }
}
