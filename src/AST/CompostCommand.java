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
public class CompostCommand extends Command{
    public CommandsList CL;
    
    public void visit(Visitor v){
        v.visitCompostCommand(this);
    }
    
    public CompostCommand(CommandsList CL){
        this.CL = CL;
    }
}
