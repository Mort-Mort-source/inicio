package com.bases_tienda.DalEntities;

public class Resena {
    private int id;
    private int idProducto;
    private int idCliente;
    private int calificacion;
    private String comentario;

    public Resena() {}

    public Resena(int id, int idProducto, int idCliente, int calificacion, String comentario) {
        this.id = id;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Resena(int idProducto, int idCliente, int calificacion, String comentario) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Reseña{" +
               "id=" + id +
               ", idProducto=" + idProducto +
               ", idCliente=" + idCliente +
               ", calificacion=" + calificacion +
               ", comentario='" + comentario + '\'' +
               '}';
    }
}

