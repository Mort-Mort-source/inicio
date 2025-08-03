package com.bases_tienda.DalEntities;

/**
 * Entidad que representa un detalle de pedido.
 */
public class DetallePedido {
    private int id;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

    public DetallePedido() {}

    public DetallePedido(int id, int idPedido, int idProducto, int cantidad, double precioUnitario) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetallePedido(int idPedido, int idProducto, int cantidad, double precioUnitario) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
               "id=" + id +
               ", idPedido=" + idPedido +
               ", idProducto=" + idProducto +
               ", cantidad=" + cantidad +
               ", precioUnitario=" + precioUnitario +
               '}';
    }
}
