package com.bases_tienda.DalEntities;

/**
 * Entidad que representa un pedido.
 */
public class Pedido {
    private int id;
    private String fecha;
    private String estado;
    private int idCliente;

    public Pedido() {}

    public Pedido(int id, String fecha, String estado, int idCliente) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public Pedido(String fecha, String estado, int idCliente) {
        this.fecha = fecha;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
               "id=" + id +
               ", fecha='" + fecha + '\'' +
               ", estado='" + estado + '\'' +
               ", idCliente=" + idCliente +
               '}';
    }
}