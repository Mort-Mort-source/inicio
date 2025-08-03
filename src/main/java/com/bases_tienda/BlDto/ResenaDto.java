package com.bases_tienda.BlDto;

public class ResenaDto {
    private int idResena;
    private int idCliente;
    private int idProducto;
    private int calificacion; // Por ejemplo, de 1 a 5
    private String comentario;

    public ResenaDto() {}

    public ResenaDto(int idResena, int idCliente, int idProducto, int calificacion, String comentario) {
        this.idResena = idResena;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
}
