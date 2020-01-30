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
public class CommandsList extends Node {

    public Command C;
    public CommandsList nextCL;

    public void visit(Visitor v) {
        v.visitCommandsList(this);
    }

    public CommandsList(Command C, CommandsList nextCL) {
        this.C = C;
        this.nextCL = nextCL;
    }
}
