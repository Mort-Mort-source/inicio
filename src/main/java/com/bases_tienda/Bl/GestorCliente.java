package com.bases_tienda.Bl;



import java.util.List;

import com.bases_tienda.BlDto.ClienteDto;
import com.bases_tienda.Dal.ProcedimientosDaoImpl;

import mx.uam.proyecto.dal.ProcedimientosDao;

public class GestorCliente {

    private ProcedimientosDao dao;

    public GestorCliente() {
        dao = new ProcedimientosDaoImpl();
    }

    public List<ClienteDto> obtenerClientesConPedidosPendientes() {
        // El DAO devuelve entidades, se convierten a DTOs
        List<ClienteDto> clientesDto = new java.util.ArrayList<>();
        dao.obtenerClientesConPedidosPendientes().forEach(c -> 
            clientesDto.add(new ClienteDto(
                c.getIdCliente(),
                c.getNombre(),
                c.getCorreo()
            ))
        );
        return clientesDto;
    }

    public boolean actualizarDireccionYTelefono(int idCliente, String nuevaDireccion, String nuevoTelefono) {
        return dao.actualizarDireccionYTelefonoCliente(idCliente, nuevaDireccion, nuevoTelefono);
    }
    
    // Puedes agregar más métodos específicos de negocio aquí
    
}
