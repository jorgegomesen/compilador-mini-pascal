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
public class Declaration extends Node {
    public Declaration nextD;
    
    public void visit(Visitor v){
        v.visitDeclaration(this);
    }
    
    public Declaration(){
        this.nextD = null;
    }
    
    public Declaration(Declaration nextD){
        this.nextD = nextD;
    }
}
