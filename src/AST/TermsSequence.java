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
public class TermsSequence extends Node {

    public Operator O;
    public Term T;
    public TermsSequence nextTS;
    public int type;

    public void visit(Visitor v) {
        v.visitTermsSequence(this);
    }

    public TermsSequence(Operator O, Term T, TermsSequence nextTS) {
        this.O = O;
        this.T = T;
        this.nextTS = nextTS;
    }

}
