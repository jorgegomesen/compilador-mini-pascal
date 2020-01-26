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
public class SequentialDeclaration extends Declaration {

    public Declaration D1;
    public Declaration D2;

    public void visit(Visitor v) {
        v.visitSequentialDeclaration(this);
    }

}
