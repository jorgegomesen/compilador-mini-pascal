/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import lexicalAnalisys.Token;
import syntaxAnalisys.Visitor;

/**
 * @author adolfo
 * @author jorgec
 */
public class SimpleType extends Type {

    public Token T;

    public void visit(Visitor v) {
        v.visitSimpleType(this);
    }

    public SimpleType(Token T) {
        this.T = T;
    }
}
