/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import compiler_.Token;
import syntaxAnalisys.Visitor;

/**
 *
 * @author jorgec
 */
public class AggregateType extends Type {

    public Token INDEX1;
    public Token INDEX2;
    public Type T;

    public void visit(Visitor v) {
        v.visitAggregateType(this);
    }

    public AggregateType(Token INDEX1, Token INDEX2, Type T) {
        this.INDEX1 = INDEX1;
        this.INDEX2 = INDEX2;
        this.T = T;
    }
}
