package com.bases_tienda.Dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bases_tienda.BlDto.ProductoDto;
import com.bases_tienda.DalEntities.Cliente;
import com.bases_tienda.DalEntities.Producto;

public class ProcedimientosDaoImpl implements ProcedimientosDao{

    private final Connection connection;

    public ProcedimientosDaoImpl() {
        DbConnection db = new DbConnection();
        db.connect();
        this.connection = db.getConnection();
    }

   

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean registrarNuevoPedido(int idCliente, List<Integer> idsProductos, List<Integer> cantidades) {
  
        String productosStr = String.join(",", idsProductos.stream().map(String::valueOf).toArray(String[]::new));
        String cantidadesStr = String.join(",", cantidades.stream().map(String::valueOf).toArray(String[]::new));

        try (CallableStatement stmt = connection.prepareCall("{CALL registrarNuevoPedido(?, ?, ?)}")) {
            stmt.setInt(1, idCliente);
            stmt.setString(2, productosStr);
            stmt.setString(3, cantidadesStr);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean registrarResena(int idCliente, int idProducto, int calificacion, String comentario) {
        try (CallableStatement stmt = connection.prepareCall("{CALL registrarResena(?, ?, ?, ?)}")) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, calificacion);
            stmt.setString(4, comentario);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public boolean cambiarEstadoPedido(int idPedido, String nuevoEstado) {
    String sql = "{CALL CambiarEstadoPedido(?, ?)}";
    try (CallableStatement stmt = connection.prepareCall(sql)) {
        stmt.setInt(1, idPedido);
        stmt.setString(2, nuevoEstado);
        stmt.execute();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public boolean eliminarResenasYActualizarPromedio(int idProducto) {
    String sql = "{CALL EliminarResenasYActualizarPromedio(?)}";
    try (CallableStatement stmt = connection.prepareCall(sql)) {
        stmt.setInt(1, idProducto);
        stmt.execute();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



@Override
    @SuppressWarnings("CallToPrintStackTrace")
public boolean actualizarDireccionYTelefonoCliente(int idCliente, String nuevaDireccion, String nuevoTelefono) {
    String sql = "{CALL ActualizarDatosCliente(?, ?, ?)}";
    try (CallableStatement stmt = connection.prepareCall(sql)) {
        stmt.setInt(1, idCliente);
        stmt.setString(2, nuevaDireccion);
        stmt.setString(3, nuevoTelefono);
        stmt.execute();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public List<Producto> obtenerProductosConBajoStock(int umbral) {
    List<Producto> productos = new ArrayList<>();
    String sql = "{CALL ReporteStockBajo()}";  // No usa parámetro umbral en el SP
    try (CallableStatement stmt = connection.prepareCall(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("id_producto"));
            p.setNombre(rs.getString("nombre"));
            p.setStock(rs.getInt("stock"));
            productos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public List<Producto> obtenerProductosPorCategoria(int idCategoria) {
    List<Producto> productos = new ArrayList<>();
    String sql = "{CALL ProductosPorCategoria()}"; // SP no filtra por categoría, ojo
    try (CallableStatement stmt = connection.prepareCall(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Producto p = new Producto();
            p.setNombre(rs.getString("producto"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            productos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public List<Cliente> obtenerClientesConPedidosPendientes() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "{CALL ClientesConPedidosPendientes()}";
    try (CallableStatement stmt = connection.prepareCall(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setId(rs.getInt("id_cliente"));
            c.setNombre(rs.getString("nombre"));
            c.setCorreo(rs.getString("correo"));
            c.setTelefono(rs.getString("telefono"));
            c.setDireccion(rs.getString("direccion"));
            clientes.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return clientes;
}

@Override
    @SuppressWarnings("CallToPrintStackTrace")
public List<Producto> obtenerTop5ProductosMejorCalificados() {
    List<Producto> productos = new ArrayList<>();
    String sql = "{CALL Top5ProductosCalificados()}";
    try (CallableStatement stmt = connection.prepareCall(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Producto p = new Producto();
            p.setNombre(rs.getString("producto"));
            p.setPromedioCalificacion(rs.getDouble("promedio_calificacion"));
            p.setTotalResenas(rs.getInt("total_reseñas"));
            productos.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productos;
}




@Override
public List<ProductoDto> getAllProductos() {
    List<ProductoDto> productosDto = new ArrayList<>();
    String sql = "{CALL Get_all_productos()}";
    try (CallableStatement stmt = connection.prepareCall(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            ProductoDto p = new ProductoDto(
                rs.getInt("id_producto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getInt("stock")
            );
            productosDto.add(p);
        }
        return productosDto;
    } catch (SQLException e) {
        e.printStackTrace();
        return productosDto;
    }
}




  

@Override
public boolean agregarProductoSinDuplicados(String nombre, String descripcion, int categoriaId, double precio, int stock) {
    String sql = "{CALL AgregarProductoSinDuplicado(?, ?, ?, ?, ?)}";
    try (CallableStatement stmt = connection.prepareCall(sql)) {
        stmt.setString(1, nombre);
        stmt.setString(2, descripcion);
        stmt.setDouble(3, precio);
        stmt.setInt(4, stock);
        stmt.setInt(5, categoriaId);
        stmt.execute();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




}


