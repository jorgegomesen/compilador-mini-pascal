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
        Error.list.add(String.format("\n\n++ Linha %d, Coluna %d ++\n- O analisador léxico identificou um erro. "
                + "Esperava-se encontrar {%s}, porém, foi encontrado {%s}", row, col,
                expected_token, current_token));
    }

    static void syntatic(int row, int col, StringBuffer expected_token, StringBuffer current_token) {
        Error.list.add(String.format("\n\n++ Linha %d, Coluna %d ++\n- O analisador sintático identificou um erro. "
                + "Esperava-se encontrar {%s}, porém, foi encontrado {%s}", row, col,
                expected_token, current_token));
    }

    static void printErrors() {
        if (!Error.list.isEmpty()) {
            int list_length = Error.list.size();
            System.out.println("\n\n*******                     ERROS                       *******");
            for (int index = 0; index < list_length; index++) {
                System.out.println(Error.list.get(index));
            }
            System.out.println("\n\n");
        }
    }

    static void clearList() {
        Error.list.clear();
    }

}
