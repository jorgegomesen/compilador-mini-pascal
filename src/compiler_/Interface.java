/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler_;

import java.io.IOException;

/**
 *
 * @author adolfo
 * @author jorgec
 */
public class Interface {

    public static void main(String[] args) throws IOException {
        String path = new String();
        Compiler_ compiler = new Compiler_();
        if (args.length <= 1 || args[0].equals("--help") || args[0].equals("-h")) {
            introduction();
            return;
        }
        path = args[1];
//        System.out.println(path);
        compiler.setFile(path);
        compiler.setReader();
        switch (args[0]) {
            case "-l":
                compiler.lexicalAnalisys();
                break;
            case "-s":
                compiler.syntaxAnalisys();
                break;
            case "-a":
                compiler.printAst();
                break;
            default:
                System.out.println("A opção especificada " + args[0] + " é inválida.");
                System.out.println("Siga as instruções logo abaixo para compilar.");

        }
    }

    private static void introduction() {
        System.out.println("COMPILADOR MINI-PASCAL");
        System.out.println("Opções de compilação disponíveis:");
        System.out.println("\t -l \t Análise léxica.");
        System.out.println("\t -s \t Análise sintática.");
        System.out.println("\t -a \t Impressão da AST.");
//		System.out.println("\t -c \t Análise de contexto.");
//		System.out.println("\t -g \t Geração de código.");
        System.out.println("");
        System.out.println("Para compilar é necessário digitar \"java Interface\", seguido da opção de compilação"
                + " e o caminho completo para o arquivo de código-fonte Mini-Pascal.");
        System.out.println("Se existirem espaços no caminho do arquivo é necessário"
                + " colocar o mesmo entre aspas duplas.");

        System.out.println("Segue abaixo exemplos de comandos para compilação até a"
                + " fase de Análise sintática:");
        System.out.println("\t java Interface -s \"C:\\Users\\User\\Documents\\arquivofonte.mpascal\"");
        System.out.println("\t java Interface -s \"/home/Documentos/arquivofonte.mpascal\"");
    }
}
