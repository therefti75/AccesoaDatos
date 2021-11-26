package com.company;

//3. Basándote en los apuntes y ejemplos de
//
//    http://chuwiki.chuidiang.org/index.php?title=Leer_y_modificar_fichero_de_propiedades_en_java
//
//crea un fichero de propiedades con el siguiente contenido:
//
//#Fichero de configuración de la aplicación X
//
//version=1.2.3
//lanzamiento=11/08/2021
//standalone=yes
//port=5858
//
//Posteriormente el programa cargará estas propiedades, las modiicará y actualizará a fichero modificando la fecha y la versión.

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args){

        Properties p = new Properties();
        p.setProperty("version","1.2.3");
        p.setProperty("lanzamiento","11/08/2021");
        p.setProperty("standalone","yes");
        p.setProperty("port","5858");

        try{
            p.store(new FileWriter("config.prop"),"Fichero");
            p.load(new FileReader("config.prop"));
            System.out.println("Versión: " + p.getProperty("version"));
            System.out.println("Lanzamiento: " + p.getProperty("lanzamiento"));
            System.out.println("Standalone: " + p.getProperty("standalone"));
            System.out.println("Puerto: " + p.getProperty("port"));
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
//
//        //Properties readP = new Properties();
//
//        try{
//            p.load(new FileReader("config.prop"));
//            System.out.println("Versión: " + p.getProperty("version"));
//            System.out.println("Lanzamiento: " + p.getProperty("lanzamiento"));
//            System.out.println("Standalone: " + p.getProperty("standalone"));
//            System.out.println("Puerto: " + p.getProperty("port"));
//        }catch(FileNotFoundException fnfe){
//            fnfe.printStackTrace();
//        }catch(IOException e){
//            e.printStackTrace();
//        }


    }
}
