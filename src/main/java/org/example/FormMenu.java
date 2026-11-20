
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

    private final ArrayList<Usuario> usuarios = new ArrayList<>();

    public FormMenu() {
        setTitle("BookUsers · Gabriel Sanabria y Nombre Apellido");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitulo.setFont(
                new Font("SansSerif", Font.BOLD, 28)
        );

        setSize(400, 320);
        setLocationRelativeTo(null);

        btnRegistrar.addActionListener(e ->
                new FormRegistro(usuarios).setVisible(true)
        );

        btnListado.addActionListener(e ->
                new FormListado(usuarios).setVisible(true)
        );


        btnCreditos.addActionListener(e ->
                new FormCreditos().setVisible(true)
        );

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new FormMenu().setVisible(true)
        );
    }
}
