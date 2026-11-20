
package org.example;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        // Configurar listado
        listaUsuarios.setModel(modelo);
        listaUsuarios.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // Cargar los usuarios al abrir la ventana
        cargarUsuarios();

        // Configurar botón de detalle
        btnVerDetalle.addActionListener(e -> verDetalle());

        // Actualizar listado cuando la ventana recupere el foco
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                cargarUsuarios();
            }
        });
    }

    private void cargarUsuarios() {

        // Guardar el usuario seleccionado antes de actualizar
        Usuario seleccionado = listaUsuarios.getSelectedValue();

        // Limpiar el modelo para evitar duplicados visuales
        modelo.clear();

        // Cargar los usuarios actuales
        for (Usuario usuario : usuarios) {
            modelo.addElement(usuario);
        }

        // Recuperar la selección anterior, si todavía existe
        if (seleccionado != null) {
            int indice = modelo.indexOf(seleccionado);

            if (indice >= 0) {
                listaUsuarios.setSelectedIndex(indice);
            }
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
