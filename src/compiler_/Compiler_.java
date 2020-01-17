/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author unknown
 */
public class Compiler_ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
//        File file = new File("Arquivos_Para_testes/teste2-tokens_erros.txt");
        File file = new File("Arquivos_Para_testes/teste3_float.txt");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        Scanner scanner = new Scanner(br);

        System.out.print("\n");

        /* Analisador Léxico */
        System.out.println("###             Análisa Léxica iniciada                     ###");
        while (br.ready()) {
//            System.out.println(scanner.scan());                
            scanner.scan();
        }
        scanner.scan();
        System.out.println("###             Finalizada!                                 ###");

        Error.printErrors();

        if (Error.list.isEmpty()) {
            Error.clearList();

            /* Analisador Sintático */
            Parser parser = new Parser(scanner);
            System.out.println("###             Análisa Sintática iniciada                  ###");

            parser.parse();

            System.out.println("###             Finalizada!                                 ###");
        }
    }

}
