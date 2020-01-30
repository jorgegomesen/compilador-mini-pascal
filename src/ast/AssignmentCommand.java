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
public class AssignmentCommand extends Command {

    public Variable V;
    public Expression E;

    public void visit(Visitor v) {
        v.visitAssignmentCommand(this);
    }

    public AssignmentCommand(Variable V, Expression E) {
        this.V = V;
        this.E = E;
    }
}
