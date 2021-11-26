package com.company;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;
import java.io.*;

//4. Realiza la aplicación que genere un fichero XML con datos de personas como en el ejercicio 1.
//5. Realiza la aplicación que lea y muestre los datos del fichero XML creado por la anterior aplicación.

class Persona{
    private int edad;
    private String nombre;

    public Persona(String nombre, int edad){
        this.edad = edad;
        this.nombre = nombre;
    }

    public void setNombre(String nombre){this.nombre=nombre;}
    public String getNombre(){return nombre;}
    public void setEdad(int edad){this.edad=edad;}
    public int getEdad(){return edad;}
}
public class Main {

    public static void main(String[] args) throws Exception{
	    createXML();
        leerXML();

    }
    public static void createXML() throws Exception{
        Persona p1 = new Persona("Gerard",19);
        Persona p2 = new Persona("Manu", 20);
        Persona p3 = new Persona("Jose", 25);
        Vector<Persona> listaPersona = new Vector<Persona>();
        listaPersona.add(p1);
        listaPersona.add(p2);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document document = dom.createDocument(null, "xml", null);
        Element raiz = document.createElement("productos");
        document.getDocumentElement().appendChild(raiz);
        Element nodoPersona = null, nodoDatos = null;
        Text texto = null;

        for(Persona persona : listaPersona){
            nodoPersona = document.createElement("persona");
            raiz.appendChild(nodoPersona);
            nodoDatos = document.createElement("nombre");
            nodoPersona.appendChild(nodoDatos);
            texto = document.createTextNode(persona.getNombre());
            nodoDatos.appendChild(texto);
            nodoDatos = document.createElement("edad");
            nodoPersona.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(persona.getEdad()));
            nodoDatos.appendChild(texto);
        }
        Source source = new DOMSource(document);
        Result resultado = new StreamResult(new File("personas.xml"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }

    public static void leerXML() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = builder.parse(new File("personas.xml"));

        NodeList personas = documento.getElementsByTagName("persona");

        for(int i = 0; i < personas.getLength(); i++){
            Node persona = personas.item(i);
            Element elemento = (Element) persona;

            System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("edad").item(0).getChildNodes().item(0).getNodeValue());

        }

    }
}
