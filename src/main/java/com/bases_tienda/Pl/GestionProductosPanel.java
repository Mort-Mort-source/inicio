package com.bases_tienda.Pl;




import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.bases_tienda.Bl.GestorProducto;
import com.bases_tienda.BlDto.ProductoDto;

import java.awt.*;
import java.util.List;



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
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnEditar.addActionListener(e -> mostrarDialogoEditar());
        btnEliminar.addActionListener(e -> eliminarProducto());
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
        JTextField txtDescripcion = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
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
                nuevo.setDescripcion(txtDescripcion.getText());
                nuevo.setPrecio(Double.parseDouble(txtPrecio.getText()));
                nuevo.setStock(Integer.parseInt(txtStock.getText()));

                gestorProducto.agregarProductoSinDuplicados(TOOL_TIP_TEXT_KEY, resultado, resultado, resultado, TOOL_TIP_TEXT_KEY) ;
            
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y Stock deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoEditar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        ProductoDto p = gestorProducto.getProductoById(id);

        JTextField txtNombre = new JTextField(p.getNombre());
        JTextField txtDescripcion = new JTextField(p.getDescripcion());
        JTextField txtPrecio = new JTextField(String.valueOf(p.getPrecio()));
        JTextField txtStock = new JTextField(String.valueOf(p.getStock()));

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Stock:"));
        panel.add(txtStock);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                p.setNombre(txtNombre.getText());
                p.setDescripcion(txtDescripcion.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setStock(Integer.parseInt(txtStock.getText()));

                gestorProducto.actualizarProducto(p);
                actualizarTabla();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y Stock deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarProducto() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            gestorProducto.eliminarProducto(id);
            actualizarTabla();
        }
    }
}




// NOTA: Cada clase como GestionProductosPanel debe ser creada como un JPanel
// que contenga la funcionalidad CRUD correspondiente con su Gestor y DTO. 
// Te puedo generar cada una a continuación si me lo pides.
