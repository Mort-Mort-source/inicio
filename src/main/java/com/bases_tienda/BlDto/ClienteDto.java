package com.bases_tienda.BlDto;



public class ClienteDto {
    private int idCliente;
    private String nombre;
    private String correo;
    private String direccion;
    private String telefono;

    public ClienteDto(int id) {}

    public ClienteDto(int idCliente, String nombre, String correo, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
}
