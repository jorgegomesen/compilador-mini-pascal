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
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        File file = new File("Arquivos_Para_testes/teste2-tokens_erros.txt");
        File file = new File("Arquivos_Para_testes/teste-tokens-em-sequencia.txt");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        Scanner scanner = new Scanner(br);
        System.out.print("\n");
        
        
        while (br.ready()) 
        {
            System.out.println(scanner.scan());                
        }
        
        System.out.println(scanner.scan());
        System.out.println(scanner.scan());
        System.out.println("\n\nola mundo");
        
        if(!Error.list.isEmpty()){
            int list_length = Error.list.size();
            System.out.println("\n\n####### ERROS ######");
            for(int index = 0; index < list_length; index++){
                System.out.println(Error.list.get(index));
            }
            System.out.println("\n\n");
        }
  
        
        
        
        
    }

}
