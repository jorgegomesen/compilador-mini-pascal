/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

import AST.Program;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import lexicalAnalisys.Scanner;
import syntaxAnalisys.Parser;
import errorHandling.Error;
import syntaxAnalisys.Printer;

/**
 *
 * @author adolfo
 * @author jorgec
 */
public class Compiler_ {

    /**
     * @param args the command line arguments
     */
    private File file;
    private FileReader reader;
    private BufferedReader br;
    private Scanner scanner;
    private Parser parser;
    private Printer printer;
    private Program program;

    public void Compiler_() {

        //***********  Testes do Léxico  *********************
//        File file = new File("Arquivos_Para_testes/teste 1 -tokens-em-sequencia.txt");
//        File file = new File("Arquivos_Para_testes/teste 2 -tokens_erros.txt");
//        File file = new File("Arquivos_Para_testes/teste 3 -float.txt"); // está entrando em loop
        //***********  Testes do Sintático  ********************* 
//        File file = new File("Arquivos_Para_testes/teste 4 -sintatico.txt");
//       File file = new File("Arquivos_Para_testes/teste 5 -sintatico_erro_1.txt");
//       File file = new File("Arquivos_Para_testes/teste 6 -sintatico_erro_2.txt");
//        System.out.print("\n");
//        setFile("Arquivos_Para_testes/teste 4 -sintatico.txt");
    }

    public void setFile(String fileName) {
        if (fileName != null) {
            file = new File(fileName);
        }
    }

    public void setReader() throws FileNotFoundException {
        if (file != null) {
            reader = new FileReader(file);
            if (reader != null) {
                br = new BufferedReader(reader);
            }
        }
    }

    public void lexicalAnalisys() throws IOException {
        scanner = new Scanner(br);
        System.out.println("###             Análisa Léxica iniciada                     ###");
        while (br.ready()) {
            scanner.scan();
        }
        scanner.scan();
        System.out.println("###             Finalizada!                                 ###");

        Error.printErrors();
    }

    public void syntaxAnalisys() throws IOException {
        lexicalAnalisys();

        if (Error.getList().isEmpty()) {
            Error.clearList();

            parser = new Parser();
            System.out.println("###             Análisa Sintática iniciada                  ###");

            program = parser.parse();

            System.out.println("###             Finalizada!                                 ###");
        }
    }

    public void printAst() throws IOException {
        syntaxAnalisys();
        System.out.println("###             Impressão da AST iniciada                  ###");
        printer = new Printer();
        printer.print(program);
        System.out.println("###             Finalizada!                                 ###");
    }

}
