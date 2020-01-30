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
public class Operator extends Node {

    public Token O;

    public void visit(Visitor v) {
        v.visitOperator(this);
    }

    public Operator(Token O) {
        this.O = O;
    }
}
