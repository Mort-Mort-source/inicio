package com.bases_tienda.Bl;


import java.util.List;

import com.bases_tienda.Dal.ProcedimientosDao;
import com.bases_tienda.Dal.ProcedimientosDaoImpl;



public class GestorPedido {

    private ProcedimientosDao dao;

    public GestorPedido() {
        dao = new ProcedimientosDaoImpl();
    }

    public boolean registrarNuevoPedido(int idCliente, List<Integer> idsProductos, List<Integer> cantidades) {
        return dao.registrarNuevoPedido(idCliente, idsProductos, cantidades);
    }

    public boolean cambiarEstadoPedido(int idPedido, String nuevoEstado) {
        return dao.cambiarEstadoPedido(idPedido, nuevoEstado);
    }

   
    
}
