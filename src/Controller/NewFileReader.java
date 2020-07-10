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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andres
 */
public class NewFileReader {
    
    
    
    
    
    
    public NewFileReader() {
    }
    
    
    public static FiniteStateMachine generateAutomata(String fileRute){
        
        String typeRegex = "^(#!)(nfe|nfd|nfn)$";
        String alphabetRegex = "^([^\n\t\r$])$|^([^\n\t$\r])-([^\n\t$\r])$";
        String statesRegex = "^([^;>\r\n\t ])([^;>\r\n\t]*)$";
        String tratitionsiiRegex = "^([^;>\r\n\t ])([^;>\r\n\t]*):([^\n\t\r])>([^;>\r\n\t ])([^;>\r\n\t]*)(;([^;>\r\n\t ])([^;>\r\n\t]*))*$";
        
        
        FiniteStateMachine automataFinito = new FiniteStateMachine();
        List<String> fileLines = new ArrayList<>();
        
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        //Se lee las lineas del archivo, se guardan en una lista
        try {                        
            file = new File(fileRute);      // Apertura del fichero y creacion de BufferedReader para poder
            fr = new FileReader(file);      // hacer una lectura comoda (disponer del metodo readLine()).
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
            try {                       // En el finally cerramos el fichero, para asegurarnos                        
                if (null != fr) {       // que se cierra tanto si todo va bien como si salta 
                    fr.close();         // una excepcion.
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        
        
    
        for (String fileLine : fileLines) {
            
            Pattern patron = Pattern.compile(typeRegex);
            Matcher m = patron.matcher(fileLine);
            boolean match = m.find();
            
            if(fileLines.indexOf(fileLine) == 0 && match){          //identifica el tipo de automata  #!dfa  #!nfa  #!nfe
                System.out.println("Primera Linea: " + fileLine + ";" + match);
                
            }else if(fileLine.equals("#alphabet") && fileLines.indexOf(fileLine) == 1){     //separador del alfabeto
                System.out.println("generating alphabet...");                               //cambiea patron Regex
                patron = Pattern.compile(alphabetRegex);
                m = patron.matcher(fileLine);
                match = m.find();
                System.out.println("---------------" + match);
                if (fileLine != "#states" && match){
                    System.out.println("---------------");
                    //capturar alphabeto
                }
                
            }else if(fileLine.equals("#states")){                   //separador de estados
                System.out.println("generating states ...");       //cambia patron Regex
                patron = Pattern.compile(statesRegex);
                m = patron.matcher(fileLine);
                match = m.find();
                
                if (match && fileLine != "#initial"){
                    System.out.println("generating Initial state ...");
                    //capturar estados
                } 
                
            }else if(fileLine.equals("#accepting")){
                System.out.println("generating accentance states ...");
                if (match && fileLine != "#transitions"){               // NO cambia patron Regex, sigue leyendo estaods
                    System.out.println("---" + fileLine + "---");
                    //capturar estados de aceptación
                }
                                                
            }else if(fileLine.equals("#transitions")){
                System.out.println("generating transitions ...");       //cambia patron Regex
                patron = Pattern.compile(tratitionsiiRegex);
                m = patron.matcher(fileLine);
                match = m.find();                
                
                if (match && fileLine != "#transitions"){
                    //capturar transiciones
                }
            
            }else{
                System.out.println("¡¡WARNING!!  :" + fileLine);
            } 
                
                
                
            //System.out.println(fileLines.get(i));
        }
        
        
        
        
        
        
        
        
        
    
    return automataFinito;
    }
    
    
    
    
 // CLASS END   
}
