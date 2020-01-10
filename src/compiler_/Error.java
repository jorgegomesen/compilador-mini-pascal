/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;
//import java.lang.RuntimeException;

import java.util.ArrayList;

/**
 *
 * @author unknown
 */
public class Error {

    static ArrayList<String> list = new ArrayList();

    static void lexical(int row, int col, StringBuffer expected_token, StringBuffer current_token) {
        Error.list.add(String.format("\n- O analisador léxico identificou um erro na linha: %d e"
                + " Coluna: %d.\n "
                + "Esperava-se encontrar <%s>, porém, foi encontrado <%s>", row, col,
                expected_token, current_token));
    }

    static void syntatic(StringBuffer expected_token, StringBuffer current_token) {
        Error.list.add(String.format("\n- O analisador sintático identificou um erro: "
                + "esperava-se encontrar %s, porém, foi encontrado %s", expected_token, current_token));
    }

}
