package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    // La única lista de usuarios de toda la aplicación: se crea aquí una sola vez
    private final ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Gabriel Sanabria y Nombre Apellido");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        setSize(400, 320);
        setLocationRelativeTo(null);

        // Por ahora los botones solo avisan; se conectan con cada ventana más adelante
        btnRegistrar.addActionListener(e -> new FormRegistro(usuarios).setVisible(true));
        btnListado.addActionListener(e -> JOptionPane.showMessageDialog(this, "Listado: pendiente"));
        btnCreditos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Créditos: pendiente"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMenu().setVisible(true));
    }
}