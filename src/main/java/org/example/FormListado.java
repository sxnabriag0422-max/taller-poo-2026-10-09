
package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class FormListado extends JFrame {

    private JPanel panelPrincipal;
    private JList<Usuario> listaUsuarios;
    private JButton btnVerDetalle;

    private final ArrayList<Usuario> usuarios;
    private final DefaultListModel<Usuario> modelo;

    public FormListado(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.modelo = new DefaultListModel<>();

        setTitle("BookUsers - Usuarios registrados");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);

        listaUsuarios.setModel(modelo);
        listaUsuarios.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        cargarUsuarios();

        btnVerDetalle.addActionListener(e -> verDetalle());
    }

    private void cargarUsuarios() {
        modelo.clear();

        for (Usuario usuario : usuarios) {
            modelo.addElement(usuario);
        }
    }

    private void verDetalle() {
        Usuario seleccionado = listaUsuarios.getSelectedValue();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debes seleccionar un usuario.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        FormDetalle detalle = new FormDetalle(seleccionado);
        detalle.setVisible(true);
    }

}
