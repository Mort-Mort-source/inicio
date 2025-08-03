package com.bases_tienda.DalEntities;

/**
 * Entidad que representa un cliente.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String correo;
    private String direccion;
    private String telefono;

    public Cliente() {}

    public Cliente(int id, String nombre, String correo, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String correo, String direccion, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", correo='" + correo + '\'' +
               ", direccion='" + direccion + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}