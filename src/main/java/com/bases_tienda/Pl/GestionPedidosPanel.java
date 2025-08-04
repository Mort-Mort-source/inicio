package com.bases_tienda.Pl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bases_tienda.Bl.GestorPedido;

public class GestionPedidosPanel extends JPanel {

    private GestorPedido gestorPedido;

    public GestionPedidosPanel() {
        gestorPedido = new GestorPedido();
        configurarUI();
    }

    private void configurarUI() {
        setLayout(new GridLayout(2, 1));

        // Panel para registrar nuevo pedido
        JPanel panelNuevoPedido = new JPanel(new GridLayout(5, 2));
        panelNuevoPedido.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Pedido"));

        JTextField txtIdCliente = new JTextField();
        JTextField txtIdsProductos = new JTextField();
        JTextField txtCantidades = new JTextField();

        panelNuevoPedido.add(new JLabel("ID Cliente:"));
        panelNuevoPedido.add(txtIdCliente);
        panelNuevoPedido.add(new JLabel("IDs Productos (separados por coma):"));
        panelNuevoPedido.add(txtIdsProductos);
        panelNuevoPedido.add(new JLabel("Cantidades (en el mismo orden, separadas por coma):"));
        panelNuevoPedido.add(txtCantidades);

        JButton btnRegistrarPedido = new JButton("Registrar Pedido");
        btnRegistrarPedido.addActionListener(e -> {
            try {
                int idCliente = Integer.parseInt(txtIdCliente.getText().trim());

                List<Integer> idsProductos = parseListaEnteros(txtIdsProductos.getText());
                List<Integer> cantidades = parseListaEnteros(txtCantidades.getText());

                if (idsProductos.size() != cantidades.size()) {
                    throw new IllegalArgumentException("La cantidad de productos y cantidades no coincide");
                }

                boolean exito = gestorPedido.registrarNuevoPedido(idCliente, idsProductos, cantidades);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Pedido registrado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el pedido", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelNuevoPedido.add(new JLabel()); // espacio
        panelNuevoPedido.add(btnRegistrarPedido);

        // Panel para cambiar estado del pedido
        JPanel panelEstadoPedido = new JPanel(new GridLayout(3, 2));
        panelEstadoPedido.setBorder(BorderFactory.createTitledBorder("Cambiar Estado de Pedido"));

        JTextField txtIdPedido = new JTextField();
        JTextField txtNuevoEstado = new JTextField();

        panelEstadoPedido.add(new JLabel("ID Pedido:"));
        panelEstadoPedido.add(txtIdPedido);
        panelEstadoPedido.add(new JLabel("Nuevo Estado:"));
        panelEstadoPedido.add(txtNuevoEstado);

        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        btnCambiarEstado.addActionListener(e -> {
            try {
                int idPedido = Integer.parseInt(txtIdPedido.getText().trim());
                String nuevoEstado = txtNuevoEstado.getText().trim();

                boolean exito = gestorPedido.cambiarEstadoPedido(idPedido, nuevoEstado);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Estado actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el estado", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Pedido debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelEstadoPedido.add(new JLabel());
        panelEstadoPedido.add(btnCambiarEstado);

        add(panelNuevoPedido);
        add(panelEstadoPedido);
    }

    private List<Integer> parseListaEnteros(String texto) throws NumberFormatException {
        String[] partes = texto.split(",");
        List<Integer> numeros = new ArrayList<>();
        for (String parte : partes) {
            numeros.add(Integer.parseInt(parte.trim()));
        }
        return numeros;
    }
}
