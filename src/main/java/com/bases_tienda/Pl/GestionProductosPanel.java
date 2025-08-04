package com.bases_tienda.Pl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bases_tienda.Bl.GestorProducto;
import com.bases_tienda.BlDto.ProductoDto;

public class GestionProductosPanel extends JPanel {

    private GestorProducto gestorProducto;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JTextField txtCategoriaBusqueda;
    private JTextField txtUmbralStock;

    public GestionProductosPanel() {
        gestorProducto = new GestorProducto();
        configurarUI();
        actualizarTabla();
    }

    private void configurarUI() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Promedio");
        modeloTabla.addColumn("Reseñas");

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnBuscarCategoria = new JButton("Buscar por Categoría");
        JButton btnBuscarBajoStock = new JButton("Buscar Bajo Stock");
        JButton btnTop5Productos = new JButton("Top 5 Productos");

        txtCategoriaBusqueda = new JTextField(5);
        txtUmbralStock = new JTextField(5);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(new JLabel("Categoría:"));
        panelBotones.add(txtCategoriaBusqueda);
        panelBotones.add(btnBuscarCategoria);
        panelBotones.add(new JLabel("Stock <"));
        panelBotones.add(txtUmbralStock);
        panelBotones.add(btnBuscarBajoStock);
        panelBotones.add(btnTop5Productos);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnActualizar.addActionListener(e -> actualizarTabla());

        btnBuscarCategoria.addActionListener(e -> {
            try {
                int idCategoria = Integer.parseInt(txtCategoriaBusqueda.getText());
                List<ProductoDto> productos = gestorProducto.obtenerProductosPorCategoria(idCategoria);
                mostrarProductosEnTabla(productos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de categoría inválido.");
            }
        });

        btnBuscarBajoStock.addActionListener(e -> {
            try {
                int umbral = Integer.parseInt(txtUmbralStock.getText());
                List<ProductoDto> productos = gestorProducto.obtenerProductosConBajoStock(umbral);
                mostrarProductosEnTabla(productos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Umbral inválido.");
            }
        });

        btnTop5Productos.addActionListener(e -> {
            List<ProductoDto> productos = gestorProducto.obtenerTop5ProductosMejorCalificados();
            mostrarProductosEnTabla(productos);
        });
    }

    private void actualizarTabla() {
        List<ProductoDto> productos = gestorProducto.getAllProductos();
        mostrarProductosEnTabla(productos);
    }

    private void mostrarProductosEnTabla(List<ProductoDto> productos) {
        modeloTabla.setRowCount(0);
        for (ProductoDto p : productos) {
            modeloTabla.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock(),
                p.getPromedioCalificacion() != null ? p.getPromedioCalificacion() : "-",
                p.getTotalResenas() != null ? p.getTotalResenas() : "-"
            });
        }
    }

    private void mostrarDialogoAgregar() {
        JTextField txtNombre = new JTextField();
        JTextField txtCategoriaId = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("ID Categoría:"));
        panel.add(txtCategoriaId);
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Stock:"));
        panel.add(txtStock);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                gestorProducto.agregarProductoSinDuplicados(
                    txtNombre.getText(),
                    Integer.parseInt(txtCategoriaId.getText()),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    txtDescripcion.getText()
                );
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio, stock o ID categoría inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
