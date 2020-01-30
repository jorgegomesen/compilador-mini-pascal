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
public class Term extends Node {

    public Factor F;
    public FactorsSequence FS;
    public int type;

    public void visit(Visitor v) {
        v.visitTerm(this);
    }

    public Term(Factor F, FactorsSequence FS) {
        this.F = F;
        this.FS = FS;
    }
}
