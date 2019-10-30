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
        throw new RuntimeException(String.format("O analisador léxico identificou um erro em [%d,%d]. "
                + "Esperava-se encontrar <%s>, porém, foi encontrado <%s>", row, col,
                expected_token, current_token));
    }

}
