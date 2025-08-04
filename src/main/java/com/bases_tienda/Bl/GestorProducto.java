package com.bases_tienda.Bl;



import java.util.List;
import com.bases_tienda.BlDto.ProductoDto;
import com.bases_tienda.Dal.ProcedimientosDaoImpl;

import mx.uam.proyecto.dal.ProcedimientosDao;

public class GestorProducto {

    private ProcedimientosDao dao;

    public GestorProducto() {
        dao = new ProcedimientosDaoImpl();
    }

    public List<ProductoDto> obtenerProductosPorCategoria(int idCategoria) {
        List<ProductoDto> productosDto = new java.util.ArrayList<>();
        dao.obtenerProductosPorCategoria(idCategoria).forEach(p -> 
            productosDto.add(new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
            ))
        );
        return productosDto;
    }

    public List<ProductoDto> obtenerProductosConBajoStock(int umbral) {
        List<ProductoDto> productosDto = new java.util.ArrayList<>();
        dao.obtenerProductosConBajoStock(umbral).forEach(p -> 
            productosDto.add(new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
            ))
        );
        return productosDto;
    }

    public List<ProductoDto> obtenerTop5ProductosMejorCalificados() {
        List<ProductoDto> productosDto = new java.util.ArrayList<>();
        dao.obtenerTop5ProductosMejorCalificados().forEach(p -> 
            productosDto.add(new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock(),
                p.getPromedioCalificacion(),   
                p.getTotalResenas()            
            ))
        );
        return productosDto;
    }

    public boolean agregarProductoSinDuplicados(String nombre, int idCategoria, double precio, int stock, String descripcion) {
        return dao.agregarProductoSinDuplicados(nombre, idCategoria, precio, stock); 
    }

    public List<ProductoDto> getAllProductos() {
        List<ProductoDto> productosDto = new java.util.ArrayList<>();
        dao.getAllProductos().forEach(p -> 
            productosDto.add(new ProductoDto(
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
            ))
        );
        return productosDto;
      
    }

    public void eliminarProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProductoDto getProductoById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actualizarProducto(ProductoDto p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   
    
}

