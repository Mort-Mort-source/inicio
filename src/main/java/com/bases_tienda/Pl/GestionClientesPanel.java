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

import com.bases_tienda.Bl.GestorCliente;
import com.bases_tienda.BlDto.ClienteDto;

public class GestionClientesPanel extends JPanel {

    private GestorCliente gestorCliente;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public GestionClientesPanel() {
        gestorCliente = new GestorCliente();
        configurarUI();
        cargarClientes();
    }

    private void configurarUI() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Correo");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        JButton btnActualizarDireccion = new JButton("Actualizar Dirección y Teléfono");
        btnActualizarDireccion.addActionListener(e -> mostrarDialogoActualizar());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnActualizarDireccion);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);
        List<ClienteDto> clientes = gestorCliente.obtenerClientesConPedidosPendientes();
        for (ClienteDto c : clientes) {
            modeloTabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getCorreo()
            });
        }
    }

    private void mostrarDialogoActualizar() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente primero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCliente = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nueva Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Nuevo Teléfono:"));
        panel.add(txtTelefono);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Actualizar Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            boolean exito = gestorCliente.actualizarDireccionYTelefono(idCliente, txtDireccion.getText(), txtTelefono.getText());
            if (exito) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
