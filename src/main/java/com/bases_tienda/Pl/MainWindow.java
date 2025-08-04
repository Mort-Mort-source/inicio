package com.bases_tienda.Pl;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Gestión de Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Productos", new GestionProductosPanel());
        tabs.addTab("Clientes", new GestionClientesPanel());  // se crea luego
        tabs.addTab("Pedidos", new GestionPedidosPanel());    // se crea luego
        tabs.addTab("Reseñas", new GestionResenasPanel());    // se crea luego

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);  // ← paréntesis cerrados correctamente
        });
    }
}
