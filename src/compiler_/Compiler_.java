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
import lexicalAnalisys.Scanner;
import syntaxAnalisys.Parser;
import errorHandling.Error;

/**
 *
 * @author adolfo
 * @author jorgec
 */
public class Compiler_ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {

        //***********  Testes do Léxico  *********************
        //File file = new File("Arquivos_Para_testes/teste 1 -tokens-em-sequencia.txt");
        //File file = new File("Arquivos_Para_testes/teste 2 -tokens_erros.txt");
        File file = new File("Arquivos_Para_testes/teste 3 -float.txt"); // está entrando em loop

        //***********  Testes do Sintático  ********************* 
//       File file = new File("Arquivos_Para_testes/teste 4 -sintatico.txt");
//       File file = new File("Arquivos_Para_testes/teste 5 -sintatico_erro_1.txt");
//       File file = new File("Arquivos_Para_testes/teste 6 -sintatico_erro_2.txt");
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

        if (Error.getList().isEmpty()) {
            Error.clearList();

            /* Analisador Sintático */
            Parser parser = new Parser(scanner);
            System.out.println("###             Análisa Sintática iniciada                  ###");

            parser.parse();

            System.out.println("###             Finalizada!                                 ###");
        }
    }

}
