package com.bases_tienda.Bl;

import com.bases_tienda.Dal.ProcedimientosDao;
import com.bases_tienda.Dal.ProcedimientosDaoImpl;

public class GestorResena {

    private ProcedimientosDao dao;

    public GestorResena() {
        dao = new ProcedimientosDaoImpl();
    }

    public boolean registrarResena(int idCliente, int idProducto, int calificacion, String comentario) {
        return dao.registrarResena(idCliente, idProducto, calificacion, comentario);
    }

    public boolean eliminarResenasYActualizarPromedio(int idProducto) {
        return dao.eliminarResenasYActualizarPromedio(idProducto);
    }

   
    
}
