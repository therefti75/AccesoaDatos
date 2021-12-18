package com.dam2.trabajo;


import com.dam2.trabajo.Producto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "proveedores")
public class Proveedor implements java.io.Serializable{
    @Id
    @GeneratedValue(generator = "sdisco")
    @SequenceGenerator(name = "sdisco", sequenceName = "seqdisco", allocationSize = 1)
    @Column
    protected int proveedor_cod;
    @Column
    protected String nombre;
    @Column
    protected String direccion;
    @Column
    protected String pais;
    @ManyToMany(mappedBy = "proveedores")
    protected List<Producto> productos = new Vector<>();

    public Proveedor(String nombre, String direccion, String pais) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pais = pais;
    }

    public Proveedor() {

    }

    public int getCod() {
        return proveedor_cod;
    }

    public void setCod(int cod) {
        this.proveedor_cod = proveedor_cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirreccion() {
        return direccion;
    }

    public void setDirreccion(String dirreccion) {
        this.direccion = dirreccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
