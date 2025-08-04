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

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnActualizar.addActionListener(e -> actualizarTabla());
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<ProductoDto> productos = gestorProducto.getAllProductos();
        for (ProductoDto p : productos) {
            modeloTabla.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
            });
        }
    }

    private void mostrarDialogoAgregar() {
        JTextField txtNombre = new JTextField();
        JTextField txtCategoriaId = new JTextField(); // Nuevo campo para ID de categoría
        JTextField txtDescripcion = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("ID Categoría:")); // Etiqueta para el nuevo campo
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
                ProductoDto nuevo = new ProductoDto();
                nuevo.setNombre(txtNombre.getText());
                nuevo.setIdCategoria(Integer.parseInt(txtCategoriaId.getText())); // Nuevo campo necesario
                nuevo.setDescripcion(txtDescripcion.getText());
                nuevo.setPrecio(Double.parseDouble(txtPrecio.getText()));
                nuevo.setStock(Integer.parseInt(txtStock.getText()));

                
                gestorProducto.agregarProductoSinDuplicados(
                txtNombre.getText(),
                Integer.parseInt(txtCategoriaId.getText()),  // Nuevo campo necesario
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText()),
                  txtDescripcion.getText()
                       );
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y Stock deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

