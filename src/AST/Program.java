/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import lexicalAnalisys.Token;
import syntaxAnalisys.Visitor;

/**
 * @author adolfo
 * @author jorgec
 */
public class Program extends Node {

    public Token T;
    public Body B;

    public void visit(Visitor v) {
        v.visitProgram(this);
    }

    public Program(Token T, Body B) {
        this.T = T;
        this.B = B;
    }
}
