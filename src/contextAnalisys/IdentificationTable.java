/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalisys;

import java.util.Stack;

/**
 *
 * @author jorgec
 */
public class IdentificationTable {

    private Stack<TL> table = new Stack<>();
    private int currentLevel = 0;

    public String indent() {
        String retorno = new String("\t");
        return retorno;
    }
}
