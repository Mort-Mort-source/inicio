package com.bases_tienda.Bl;



import java.util.List;

import com.bases_tienda.BlDto.ClienteDto;
import com.bases_tienda.Dal.ProcedimientosDao;
import com.bases_tienda.Dal.ProcedimientosDaoImpl;



public class GestorCliente {

    @SuppressWarnings("FieldMayBeFinal")
    private ProcedimientosDao dao;

    public GestorCliente() {
     dao = new ProcedimientosDaoImpl();
    }

    public List<ClienteDto> obtenerClientesConPedidosPendientes() {
      
        List<ClienteDto> clientesDto = new java.util.ArrayList<>();
        dao.obtenerClientesConPedidosPendientes().forEach(c -> 
            clientesDto.add(new ClienteDto(
                c.getId(),
                c.getNombre(),
                c.getCorreo()
            ))
        );
        return clientesDto;
    }

    public boolean actualizarDireccionYTelefono(int idCliente, String nuevaDireccion, String nuevoTelefono) {
        return dao.actualizarDireccionYTelefonoCliente(idCliente, nuevaDireccion, nuevoTelefono);
    }
    
    
    
}
