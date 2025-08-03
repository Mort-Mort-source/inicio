package com.bases_tienda.Dal;



import java.util.List;

import com.bases_tienda.DalEntities.Cliente;
import com.bases_tienda.DalEntities.Producto;

public interface ProcedimientosDao {

    // 1. Registrar un nuevo pedido
    boolean registrarNuevoPedido(int idCliente, List<Integer> idsProductos, List<Integer> cantidades);

    // 2. Registrar una reseña
    boolean registrarResena(int idCliente, int idProducto, int calificacion, String comentario);

    // 3. Cambiar el estado de un pedido
    boolean cambiarEstadoPedido(int idPedido, String nuevoEstado);

    // 4. Eliminar reseñas de un producto y actualizar su promedio
    boolean eliminarResenasYActualizarPromedio(int idProducto);

    // 5. Agregar producto evitando duplicados por nombre y categoría
    boolean agregarProductoSinDuplicados(String nombre, int categoriaId, double precio, int stock);

    // 6. Actualizar dirección y teléfono de un cliente
    boolean actualizarDireccionYTelefonoCliente(int idCliente, String nuevaDireccion, String nuevoTelefono);

    // 7. Reporte de productos con bajo stock
    List<Producto> obtenerProductosConBajoStock(int umbral);

    // --- CONSULTAS almacenadas como SPs ---

    // 8. Productos disponibles por categoría, ordenados por precio
    List<Producto> obtenerProductosPorCategoria(int idCategoria);

    // 9. Clientes con pedidos pendientes y total de compras
    List<Cliente> obtenerClientesConPedidosPendientes();

    // 10. Top 5 productos con mejor calificación promedio
    List<Producto> obtenerTop5ProductosMejorCalificados();
}
