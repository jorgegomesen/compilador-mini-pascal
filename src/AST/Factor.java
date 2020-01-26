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
public class Factor extends Node {

    public int type;

    public void visit(Visitor v) {
        v.visitFactor(this);
    }
}
