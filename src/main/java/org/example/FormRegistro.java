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

    // Ruta de la foto elegida (se llena cuando se carga la foto)
    private String rutaFoto = "";

    public FormRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers · Registro de usuario");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        llenarTiposId();
        pack();
        setLocationRelativeTo(null);

        btnGuardar.addActionListener(e -> guardar());
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

    private void guardar() {
        String tipoId = (String) cmbTipoId.getSelectedItem();
        String numeroId = txtNumeroId.getText().trim();
        String primerNombre = txtPrimerNombre.getText().trim();
        String segundoNombre = txtSegundoNombre.getText().trim();
        String primerApellido = txtPrimerApellido.getText().trim();
        String segundoApellido = txtSegundoApellido.getText().trim();
        // El JPasswordField no tiene getText(): se lee como arreglo de caracteres
        String password = new String(txtPassword.getPassword());
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        // Campos obligatorios
        if (numeroId.isEmpty() || primerNombre.isEmpty() || primerApellido.isEmpty()
                || password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // El correo debe contener @
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener @.",
                    "Correo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // El número solo lleva dígitos, excepto pasaporte (41) y documento extranjero (42)
        boolean debeSerNumerico = !(tipoId.startsWith("41") || tipoId.startsWith("42"));
        if (debeSerNumerico && !Validaciones.soloDigitos(numeroId)) {
            JOptionPane.showMessageDialog(this, "El número de identificación solo debe tener dígitos.",
                    "Número inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // El id lo asigna el programa dentro del constructor de Usuario
        Usuario u = new Usuario(tipoId, numeroId, primerNombre, segundoNombre,
                primerApellido, segundoApellido, password, telefono, correo, rutaFoto);
        usuarios.add(u);

        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.\nUsername: " + u.getUsername());
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        cmbTipoId.setSelectedIndex(2);
        txtNumeroId.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        lblUsername.setText("-");
        rutaFoto = "";
        lblFoto.setIcon(null);
        lblFoto.setText("Sin foto");
    }
}