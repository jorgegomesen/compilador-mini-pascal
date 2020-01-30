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
public class IterativeCommand extends Command {

    public Expression E;
    public Command C;

    public void visit(Visitor v) {
        v.visitIterativeCommand(this);
    }

    public IterativeCommand(Expression E, Command C) {
        this.E = E;
        this.C = C;
    }
}
