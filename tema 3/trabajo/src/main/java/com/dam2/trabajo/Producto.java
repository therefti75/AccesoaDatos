package com.dam2.trabajo;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "productos")
public class Producto implements java.io.Serializable{
    @Id
    @GeneratedValue(generator = "sproducto")
    @SequenceGenerator(name = "sproducto",sequenceName = "seqproducto",allocationSize = 1)
    @Column
    protected int producto_cod;
    @Column
    protected String nombre;
    @Column
    protected String descripcion;
    @Column
    protected double precio;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "producto_proveedor",
            joinColumns = {@JoinColumn(name="producto_cod")},
            inverseJoinColumns = {@JoinColumn(name = "proveedor_cod")})
    protected List<Proveedor> proveedores = new Vector<>();

    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto() {

    }

    public int getCod() {
        return producto_cod;
    }

    public void setCod(int producto_cod) {
        this.producto_cod = producto_cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

}
