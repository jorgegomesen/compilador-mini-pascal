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
public class ConditionalCommand extends Command{
    public Expression E;
    public Command C1, C2;
    
    public void visit(Visitor v){
        v.visitConditionalCommand(this);
    }
    
    public ConditionalCommand(Expression E, Command C1, Command C2){
        this.E = E;
        this.C1 = C1;
        this.C2 = C2;
    }
}
