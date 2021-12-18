package com.dam2.trabajo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static Scanner ent = new Scanner(System.in);
    public static Session getS(){
        SessionFactory sf = HibernateUtil.getSF();
        Session s = sf.openSession();
        return s;
    }
    public static void main(String[] args) {
        boolean cerrar = true;

        int n;
        do{
            System.out.println("Elija una opción");
            System.out.println("1. Producto");
            System.out.println("2. Proveedores");
            System.out.println("0. Salir del Programa");

            n = ent.nextInt();

            switch (n) {
                case 1 : Producto(); break;
                case 2 : Proveedor(); break;
                default : System.out.println("Eleccion no válida");
            }

        }while (n != 0);
    }

    public static void Producto(){
        int n;
        do{
            System.out.println("1. Ver un Producto");
            System.out.println("2. Añadir Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Añadir referencia a Proveedor");
            System.out.println("0. Volver atras");

            n = ent.nextInt();

            switch (n) {
                case 1 : verProducto(); break;
                case 2 : añadirProducto(); break;
                case 3 : modificarProducto(); break;
                case 4 : eliminarProducto(); break;
                case 5 : añadirReferencia(); break;
                default : System.out.println("Eleccion no válida");
            }
        }while (n != 0);

    }

    public static void Proveedor(){

        int n;
        do{
            System.out.println("1. Ver un Proveedor");
            System.out.println("2. Añadir Proveedor");
            System.out.println("3. Modificar Proveedor");
            System.out.println("4. Eliminar Proveedor");
            System.out.println("0. Volver atras");

            n = ent.nextInt();

            switch (n) {
                case 1 : verProveedor(); break;
                case 2 : añadirProveedor(); break;
                case 3 : modificarProveedor(); break;
                case 4 : eliminarProveedor(); break;
                default : System.out.println("Eleccion no válida");
            }
        }while (n != 0);

    }

    public static void añadirProducto(){
        String nombre, descripcion;
        double precio;
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Introduce nombre del nuevo producto");
        nombre = ent.nextLine();
        ent.nextLine();
        System.out.println("Introduce descripcion del producto");
        descripcion = ent.nextLine();
        System.out.println("Introduce precio del producto");
        precio = ent.nextDouble();

        Producto p = new Producto(nombre,descripcion,precio);
        s.save(p);
        t.commit();
        if (s != null) s.close();

    }

    public static void añadirProveedor(){
        String nombre, direccion, pais;
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Introduce nombre del nuevo proveedor");
        nombre = ent.nextLine();
        ent.nextLine();
        System.out.println("Introduce la direccion del proveedor");
        direccion = ent.nextLine();
        System.out.println("Introduce el pais del producto");
        pais = ent.nextLine();

        Proveedor p = new Proveedor(nombre,direccion,pais);
        s.save(p);
        t.commit();
        if (s != null) s.close();


    }

    public static void modificarProducto(){
        String producto,nombre, descripcion;
        double precio;
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Elige el producto a modificar");
        verProducto();
        producto = ent.nextLine();
        System.out.println("Introduce el nuevo nombre del producto");
        nombre = ent.nextLine();
        System.out.println("Introduce la nueva descripcion del producto");
        descripcion = ent.nextLine();
        System.out.println("Introduce el nuevo precio del producto");
        precio = ent.nextDouble();
        Query<Producto> q = s.createQuery("update Producto set nombre=:nombre, descripcion=:descripcion,precio=:precio where nombre=:producto");
        q.setParameter("nombre",nombre);
        q.setParameter("descripcion",descripcion);
        q.setParameter("precio",precio);
        q.setParameter("producto",producto);
        q.executeUpdate();
        t.commit();
        if (s != null) s.close();

    }

    public static void modificarProveedor(){
        String proveedor,nombre, direccion, pais;
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Elige el proveedor a modificar");
        verProducto();
        proveedor = ent.nextLine();
        ent.nextLine();
        System.out.println("Introduce el nuevo nombre del proveedor");
        nombre = ent.nextLine();
        System.out.println("Introduce la nueva dirreccion del proveedor");
        direccion = ent.nextLine();
        System.out.println("Introduce el nuevo pais del proveedor");
        pais = ent.nextLine();
        Query<Producto> q = s.createQuery("update Proveedor set nombre=:nombre, direccion=:direccion,pais=:pais where nombre=:proveedor");
        q.setParameter("nombre",nombre);
        q.setParameter("direccion",direccion);
        q.setParameter("pais",pais);
        q.setParameter("proveedor",proveedor);
        q.executeUpdate();
        t.commit();
        if (s != null) s.close();



    }

    public static void eliminarProducto(){
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Elegir producto a borrar");
        verProducto();
        String producto = ent.nextLine();
        Query q = s.createQuery("delete from Producto where nombre = :producto");
        q.setParameter("producto",producto);
        q.executeUpdate();
        t.commit();
        if (s != null) s.close();


    }

    public static void eliminarProveedor(){
        Session s = getS();
        Transaction t = s.beginTransaction();
        System.out.println("Elegir producto a borrar");
        verProveedor();
        String proveedor = ent.nextLine();
        Query q = s.createQuery("delete from Proveedor where nombre = :proveedor");
        q.setParameter("proveedor",proveedor);
        q.executeUpdate();
        t.commit();
        if (s != null) s.close();

    }

    public static void añadirReferencia(){
        Session s = getS();
        Transaction t = s.beginTransaction();
        boolean save = true;
        System.out.println("Elegir producto");
        verProducto();
        String producto = ent.nextLine();
        System.out.println("Elegir proveedor para el producto");
        verProveedor();
        int id=ent.nextInt();
        Query<Producto> q = s.createQuery("from Producto  where nombre = :producto");
        q.setParameter("producto",producto);
        Producto p = q.getSingleResult();
        Proveedor pro = s.get(Proveedor.class,id);
        for (int i = 0; i<p.getProveedores().size();i++){
            if (p.getProveedores().get(i) == pro){
                System.out.println("El producto ya tiene a este proveedor");
                save = false;
                break;
            }
        }
        if (save){
            p.getProveedores().add(pro);
            s.save(p);
            t.commit();
            if (s != null) s.close();
        }

    }

    public static void verProducto(){
        Session s = getS();
        Query q = s.createQuery("select producto_cod,nombre from Producto ");
        System.out.println(q);
        if (s != null) s.close();

    }

    public static void verProveedor(){
        Session s = getS();
        Query q = s.createQuery("select proveedor_cod,nombre from Proveedor ");
        System.out.println(q);
        if (s != null) s.close();

    }
}
