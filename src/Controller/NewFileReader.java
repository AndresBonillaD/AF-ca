/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.FiniteStateMachine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andres
 */
public class NewFileReader {

    public NewFileReader() {
    }
    
    
    public static FiniteStateMachine generateAutomata(String fileRute){
        
        
        FiniteStateMachine automataFinito = new FiniteStateMachine();
        List<String> fileLines = new ArrayList<>();
        
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        

        //Se lee las lineas del archivo y se extraen los datos
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            file = new File(fileRute);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {
                //System.out.println(linea);
                fileLines.add(linea); // añade archivo a la lista 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        
    
        for (String fileLine : fileLines) {
            //identifica el tipo de automata  #!dfa  #!nfa  #!nfe
            if (fileLine.equals("#!dfa") && fileLine.indexOf(fileLine) != 0) {
                System.out.println("-- dfa --");
            } else if (fileLine.equals("#!nfa")) {
                System.out.println("-- nfa --");
            } else if (fileLine.equals("#!nfe")) {
                System.out.println("-- nfe --");
            } else {
                System.out.println("Archivo no aceptado - Error en tipo de automata linea 0");
                //break;
            }
            
            if(fileLine.equals("#alphabet")){
                System.out.println("generating alphabet...");
            } 
            
            //System.out.println(fileLines.get(i));
        }
        
        
        
        
        
        
        
        
        
    
    return automataFinito;
    }
    
    
    
    
    
}
