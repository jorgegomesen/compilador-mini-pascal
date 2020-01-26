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
public class FactorsSequence extends Node {
    public Operator O;
    public Factor F;
    public FactorsSequence nextFS;
    public int type;
    
    public void visit(Visitor v){
        v.visitFactorsSequence(this);
    }
    
    public FactorsSequence(Operator O, Factor F, FactorsSequence nextFS){
        this.O = O;
        this.F = F;
        this.nextFS = nextFS;
    }
}
