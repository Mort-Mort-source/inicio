package com.bases_tienda.BlDto;



import java.util.Date;
import java.util.List;

public class PedidoDto {
    private int idPedido;
    private int idCliente;
    private Date fecha;
    private String estado;
    private List<DetallePedidoDto> detalles;

    public PedidoDto() {}

    public PedidoDto(int idPedido, int idCliente, Date fecha, String estado, List<DetallePedidoDto> detalles) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.detalles = detalles;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetallePedidoDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDto> detalles) {
        this.detalles = detalles;
    }
}

