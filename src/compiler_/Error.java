/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;
//import java.lang.RuntimeException;

/**
 *
 * @author unknown
 */
public class Error {

    static void lexical(int row, int col, StringBuffer expected_token, StringBuffer current_token) {
        throw new RuntimeException(String.format("\nO analisador léxico identificou um erro na linha: %d e"
                + " Coluna: %d.\n "
                + "Esperava-se encontrar <%s>, porém, foi encontrado <%s>\n", row, col,
                expected_token, current_token));
    }

    static void syntatic(StringBuffer expected_token, StringBuffer current_token) {
        throw new RuntimeException(String.format("Error: %s expected here, but got %s", expected_token, current_token));
    }

}
