//1. Realiza una aplicación que muestre las propiedades del usuario en el sistema.

import java.util.*;
import javax.sql.rowset.spi.SyncResolver;
import java.io.*;

public class Main {
    
    public static void main(String args[]){
        System.out.println("Directorio principal del usuario: " + System.getProperty("user.home"));
        System.out.println("Directorio de trabajo del usuario: " + System.getProperty("user.dir"));
        System.out.println("User account name: " + System.getProperty("user.name"));


    }
}
