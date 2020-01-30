/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import syntaxAnalisys.Visitor;

/**
 * @author adolfo
 * @author jorgec
 */
public class Selector extends Node {

    public Expression E;
    public Selector nextS;

    public void visit(Visitor v) {
        v.visitSelector(this);
    }

    public Selector(Expression E, Selector nextS) {
        this.E = E;
        this.nextS = nextS;
    }
}
