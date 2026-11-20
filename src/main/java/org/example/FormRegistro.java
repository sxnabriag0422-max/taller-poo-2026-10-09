package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class FormRegistro extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;

    // La lista llega desde FormMenu: es la misma para todas las ventanas
    private final ArrayList<Usuario> usuarios;

    public FormRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers · Registro de usuario");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        llenarTiposId();
        pack();
        setLocationRelativeTo(null);
    }

    // Tipos de documento de persona natural (codificación DIAN)
    private void llenarTiposId() {
        cmbTipoId.addItem("11 - Registro civil de nacimiento");
        cmbTipoId.addItem("12 - Tarjeta de identidad");
        cmbTipoId.addItem("13 - Cédula de ciudadanía");
        cmbTipoId.addItem("21 - Tarjeta de extranjería");
        cmbTipoId.addItem("22 - Cédula de extranjería");
        cmbTipoId.addItem("41 - Pasaporte");
        cmbTipoId.addItem("42 - Documento de identificación extranjero");
        cmbTipoId.addItem("47 - Permiso Especial de Permanencia (PEP)");
        cmbTipoId.addItem("91 - NUIP");
        cmbTipoId.setSelectedIndex(2);
    }
}