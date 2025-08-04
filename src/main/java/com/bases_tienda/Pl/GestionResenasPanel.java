package com.bases_tienda.Pl;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bases_tienda.Bl.GestorResena;

public class GestionResenasPanel extends JPanel {

    private GestorResena gestorResena;

    public GestionResenasPanel() {
        gestorResena = new GestorResena();
        configurarUI();
    }

    private void configurarUI() {
        setLayout(new GridLayout(2, 1));

        // Panel para registrar reseña
        JPanel panelRegistrar = new JPanel(new GridLayout(5, 2));
        panelRegistrar.setBorder(BorderFactory.createTitledBorder("Registrar Reseña"));

        JTextField txtIdCliente = new JTextField();
        JTextField txtIdProducto = new JTextField();
        JTextField txtCalificacion = new JTextField();
        JTextField txtComentario = new JTextField();

        panelRegistrar.add(new JLabel("ID Cliente:"));
        panelRegistrar.add(txtIdCliente);
        panelRegistrar.add(new JLabel("ID Producto:"));
        panelRegistrar.add(txtIdProducto);
        panelRegistrar.add(new JLabel("Calificación (1-5):"));
        panelRegistrar.add(txtCalificacion);
        panelRegistrar.add(new JLabel("Comentario:"));
        panelRegistrar.add(txtComentario);

        JButton btnRegistrar = new JButton("Registrar Reseña");
        btnRegistrar.addActionListener(e -> {
            try {
                int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
                int idProducto = Integer.parseInt(txtIdProducto.getText().trim());
                int calificacion = Integer.parseInt(txtCalificacion.getText().trim());
                String comentario = txtComentario.getText().trim();

                if (calificacion < 1 || calificacion > 5) {
                    throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
                }

                boolean exito = gestorResena.registrarResena(idCliente, idProducto, calificacion, comentario);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Reseña registrada exitosamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar reseña", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelRegistrar.add(new JLabel());
        panelRegistrar.add(btnRegistrar);

        // Panel para eliminar reseñas y actualizar promedio
        JPanel panelEliminar = new JPanel(new GridLayout(2, 2));
        panelEliminar.setBorder(BorderFactory.createTitledBorder("Eliminar Reseñas y Actualizar Promedio"));

        JTextField txtIdProductoEliminar = new JTextField();

        panelEliminar.add(new JLabel("ID Producto:"));
        panelEliminar.add(txtIdProductoEliminar);

        JButton btnEliminar = new JButton("Eliminar Reseñas");
        btnEliminar.addActionListener(e -> {
            try {
                int idProducto = Integer.parseInt(txtIdProductoEliminar.getText().trim());
                boolean exito = gestorResena.eliminarResenasYActualizarPromedio(idProducto);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Reseñas eliminadas y promedio actualizado");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar reseñas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelEliminar.add(new JLabel());
        panelEliminar.add(btnEliminar);

        // Añadir ambos paneles
        add(panelRegistrar);
        add(panelEliminar);
    }
}
