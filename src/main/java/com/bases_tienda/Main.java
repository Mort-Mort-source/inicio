package com.bases_tienda;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.bases_tienda.Pl.GestionProductosPanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Productos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // Aquí agregas tu panel al frame
            frame.setContentPane(new GestionProductosPanel());

            frame.setVisible(true);
        });
    }
}
