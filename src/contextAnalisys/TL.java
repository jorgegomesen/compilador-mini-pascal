/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalisys;

import AST.Node;
import lexicalAnalisys.Token;

/**
 *
 * @author jorgec
 */
public class TL {

    public int level;
    public Token identifier; // Alternamos para Token para dar mais informações na geração de erros.
    public boolean used; // se o identificador declarado foi utilizado ou não
    public Node attribute;

    public String toString() {
        String result = new String((new Integer(level)).toString() + "\t "
                + identifier.spelling + "\t\t "
                + used + "\t\t " + attribute);
        return result;
    }
}
