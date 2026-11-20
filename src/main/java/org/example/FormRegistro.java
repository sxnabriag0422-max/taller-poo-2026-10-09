
package org.example;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.io.File;
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

    private final ArrayList<Usuario> usuarios;
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
        btnCargarFoto.addActionListener(e -> cargarFoto());

        DocumentListener actualizador = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarUsername();
            }
        };

        txtPrimerNombre.getDocument()
                .addDocumentListener(actualizador);

        txtPrimerApellido.getDocument()
                .addDocumentListener(actualizador);

        txtSegundoApellido.getDocument()
                .addDocumentListener(actualizador);
    }

    private void actualizarUsername() {
        String username = Usuario.construirUsername(
                txtPrimerNombre.getText(),
                txtPrimerApellido.getText(),
                txtSegundoApellido.getText()
        );

        lblUsername.setText(
                username.isEmpty() ? "-" : username
        );
    }

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

    private void cargarFoto() {
        JFileChooser selector = new JFileChooser();

        selector.setAcceptAllFileFilterUsed(false);
        selector.setFileFilter(
                new FileNameExtensionFilter(
                        "Imágenes PNG y JPG",
                        "png", "jpg"
                )
        );

        int resultado = selector.showOpenDialog(this);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = selector.getSelectedFile();

        if (!Validaciones.esFotoValida(archivo.getName())) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una imagen .png o .jpg.",
                    "Foto inválida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        ImageIcon original =
                new ImageIcon(archivo.getAbsolutePath());

        if (original.getIconWidth() <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo leer la imagen seleccionada.",
                    "Foto inválida",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Image escalada = original.getImage()
                .getScaledInstance(
                        120,
                        120,
                        Image.SCALE_SMOOTH
                );

        lblFoto.setIcon(new ImageIcon(escalada));
        lblFoto.setText("");

        rutaFoto = archivo.getAbsolutePath();
    }

    private void guardar() {

        String tipoId = (String) cmbTipoId.getSelectedItem();
        String numeroId = txtNumeroId.getText().trim();
        String primerNombre = txtPrimerNombre.getText().trim();
        String segundoNombre = txtSegundoNombre.getText().trim();
        String primerApellido = txtPrimerApellido.getText().trim();
        String segundoApellido = txtSegundoApellido.getText().trim();
        String password = new String(txtPassword.getPassword());
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        // 1. Validar campos obligatorios
        if (numeroId.isEmpty()
                || primerNombre.isEmpty()
                || primerApellido.isEmpty()
                || password.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2. Validar correo
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El correo debe contener @.",
                    "Correo inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 3. Validar identificación
        boolean debeSerNumerico =
                !(tipoId.startsWith("41")
                        || tipoId.startsWith("42"));

        if (debeSerNumerico
                && !Validaciones.soloDigitos(numeroId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "El número de identificación solo debe tener dígitos.",
                    "Número inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 4. Validar teléfono
        if (!telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El teléfono debe contener únicamente números.",
                    "Teléfono inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 5. Validar contraseña
        if (!password.matches(
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "La contraseña debe contener:\n"
                            + "- Mínimo 8 caracteres.\n"
                            + "- Una letra mayúscula.\n"
                            + "- Una letra minúscula.\n"
                            + "- Un número.\n"
                            + "- Un símbolo especial.",
                    "Contraseña insegura",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 6. Evitar identificaciones duplicadas
        for (Usuario usuarioExistente : usuarios) {

            boolean mismoTipo =
                    usuarioExistente.getTipoIdentificacion()
                            .equals(tipoId);

            boolean mismoNumero =
                    usuarioExistente.getNumeroIdentificacion()
                            .equalsIgnoreCase(numeroId);

            if (mismoTipo && mismoNumero) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un usuario registrado con "
                                + "este tipo y número de identificación.",
                        "Identificación duplicada",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        // 7. Crear y registrar usuario
        Usuario u = new Usuario(
                tipoId,
                numeroId,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                password,
                telefono,
                correo,
                rutaFoto
        );

        usuarios.add(u);

        JOptionPane.showMessageDialog(
                this,
                "Usuario registrado correctamente.\n"
                        + "Username: " + u.getUsername(),
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE
        );

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
