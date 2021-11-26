import java.sql.*;
import java.util.*;

public class Main {
    /*Requisitos

-  La aplicación deberá conectar con una Base de Datos de forma transparente para el usuario, de forma que los datos de conexión puedan configurarse en un fichero aparte
(fichero *properties*), puede tener interfaz gráfica y responder al patrón MVC (Modelo-Vista-Controlador)

            -  El usuario tiene que poder dar de alta, modificar y eliminar objetos de, al menos, dos clases relacionadas entre sí
-  Trabajar con, al menos, una relación N-M entre objetos de la aplicación
-  Añadir alguna forma de búsqueda*/
    public static Scanner ent = new Scanner(System.in);
    public static void main(String[] args)throws SQLException{
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vehiculo", "postgres","bitnami");

        int n;
        do {
            System.out.println("ELIJA");
            System.out.println("1. Crear un vehiculo");
            System.out.println("2. Crear un conductor");
            System.out.println("3. Modificar vehiculo");
            System.out.println("4. Modificar conductor");
            System.out.println("5. Eliminar vehiculo");
            System.out.println("6. Eliminar conductor");
            System.out.println("7. Salir");
            n = ent.nextInt();

            switch (n) {
                case 1 : crearVehiculo(conexion); break;
                case 2 : crearConductor(conexion); break;
                case 3 : modificarVehiculo(conexion); break;
                case 4 : modificarConductor(conexion); break;
                case 5 : eliminarVehiculo(conexion); break;
                case 6 : eliminarConductor(conexion); break;
                default : System.out.println("Eleccion no válida");
            }
        }while (n != 7);
    }

    public static void crearVehiculo(Connection conexion){

        System.out.println("Nombre del vehiculo: ");
        String vehiculo = ent.nextLine();
        String sentenciaSql = "INSERT INTO VEHICULOS(NOMBRE)VALUES(?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, vehiculo);
            sentencia.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }

    }

    public static void crearConductor(Connection conexion){

        System.out.println("Nombre del conductor: ");
        String conductor = ent.nextLine();
        System.out.println("Código del vehiculo: ");
        int vehiculo = ent.nextInt();
        String sentenciaSql = "INSERT INTO CONDUCTORES(NOMBRE,COD_VEHICULO)VALUES(?,?)";
        PreparedStatement sentencia = null;
        try{
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, conductor);
            sentencia.setInt(2, vehiculo);
            sentencia.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }

    }

    public static void modificarVehiculo(Connection conexion){

        System.out.println("Nombre del vehiculo que quieres modificar: ");
        String vehiculo1 = ent.nextLine();
        System.out.println("Nombre nuevo del vehiculo: ");
        String vehiculo2 = ent.nextLine();
        String sentenciaSql = "UPDATE VEHICULOS SET NOMBRE=? WHERE NOMBRE=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(2, vehiculo1);
            sentencia.setString(1, vehiculo2);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }

    }

    public static void modificarConductor(Connection conexion){

        System.out.println("Nombre del conductor que quieres modificar: ");
        String conductor1 = ent.nextLine();
        System.out.println("Nombre nuevo del conductor: ");
        String conductor2 = ent.nextLine();
        System.out.println("Codigo nuevo del vehiculo");
        int cod = ent.nextInt();
        String sentenciaSql = "UPDATE CONDUCTORES SET NOMBRE=?, COD_VEHICULO=? WHERE NOMBRE=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(3, conductor1);
            sentencia.setString(1, conductor2);
            sentencia.setInt(2,cod);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }

    }


    public static void eliminarVehiculo(Connection conexion){

        System.out.println("Nombre del vehiculo que quieres borrar");
        String vehiculo = ent.nextLine();
        String sentenciaSql = "DELETE FROM VEHICULOS WHERE NOMBRE=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, vehiculo);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }


    }

    public static void eliminarConductor(Connection conexion){

        System.out.println("Nombre del conductor que quieres borrar");
        String conductor = ent.nextLine();
        String sentenciaSql = "DELETE FROM CONDUCTORES WHERE NOMBRE=?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, conductor);
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null)
                try {
                    sentencia.close();
                    conexion.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
        }


    }
}
