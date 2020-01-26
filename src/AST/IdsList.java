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
public class IdsList extends Node {
    public Token T;
    public IdsList nextIL;
    
    public void visit(Visitor v){
        v.visitIdsList(this);
    }
    
    public IdsList(Token T){
        this.T = T;
    }
    
    public IdsList(Token T, IdsList nextIL){
        this.T = T;
        this.nextIL = nextIL;
    }
}
