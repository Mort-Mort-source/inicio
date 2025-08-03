package com.bases_tienda.DalEntities;

/**
 * Entidad que representa un producto.
 */
public class Producto {
  



private int id;
private String nombre;
private String descripcion;
private double precio;
private int stock;
private double promedioCalificacion;
private int totalResenas;
private int idCategoria;

public Producto() {}



public Producto(int id, String nombre, String descripcion, double precio, int stock, double promedioCalificacion, int totalResenas, int idCategoria) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
    this.promedioCalificacion = promedioCalificacion;
    this.totalResenas = totalResenas;
    this.idCategoria = idCategoria;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
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

public int getStock() {
    return stock;
}

public void setStock(int stock) {
    this.stock = stock;
}

public double getPromedioCalificacion() {
    return promedioCalificacion;
}

public void setPromedioCalificacion(double promedioCalificacion) {
    this.promedioCalificacion = promedioCalificacion;
}

public int getTotalResenas() {
    return totalResenas;
}

public void setTotalResenas(int totalResenas) {
    this.totalResenas = totalResenas;
}

public int getIdCategoria() {
    return idCategoria;
}

public void setIdCategoria(int idCategoria) {
    this.idCategoria = idCategoria;
}




}