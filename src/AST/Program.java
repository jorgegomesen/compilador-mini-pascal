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
public class Program extends Node {
    public Identifier I;
    public Body B;
    
    
    public void visit(Visitor v){
        v.visitProgram(this);
    }
    
    public Program(Identifier I, Body B){
        this.I = I;
        this.B = B;
    }
}
