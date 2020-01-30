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
public class Body extends Node {

    public Declaration D;
    public Command C;

    public void visit(Visitor v) {
        v.visitBody(this);
    }

    public Body(Declaration D, Command C) {
        this.D = D;
        this.C = C;
    }
}
