
package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FormDetalle extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JTextArea txtInformacion;
    private JButton btnGenerarPDF;

    private final Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;

        setTitle("BookUsers - Detalle del usuario");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 650);
        setLocationRelativeTo(null);

        mostrarInformacion();
        mostrarFoto();

        btnGenerarPDF.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "La generación del PDF se implementará en la Fase 16.",
                        "BookUsers",
                        JOptionPane.INFORMATION_MESSAGE
                )
        );
    }

    private void mostrarInformacion() {
        String informacion =
                "ID: " + usuario.getId() + "\n\n" +
                        "Tipo de identificación: " +
                        usuario.getTipoIdentificacion() + "\n\n" +
                        "Número de identificación: " +
                        usuario.getNumeroIdentificacion() + "\n\n" +
                        "Primer nombre: " +
                        usuario.getPrimerNombre() + "\n\n" +
                        "Segundo nombre: " +
                        usuario.getSegundoNombre() + "\n\n" +
                        "Primer apellido: " +
                        usuario.getPrimerApellido() + "\n\n" +
                        "Segundo apellido: " +
                        usuario.getSegundoApellido() + "\n\n" +
                        "Nombre completo: " +
                        usuario.getNombreCompleto() + "\n\n" +
                        "Username: " +
                        usuario.getUsername() + "\n\n" +
                        "Teléfono: " +
                        usuario.getTelefono() + "\n\n" +
                        "Correo electrónico: " +
                        usuario.getCorreo();

        txtInformacion.setText(informacion);
        txtInformacion.setEditable(false);
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setCaretPosition(0);
    }

    private void mostrarFoto() {
        lblFoto.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        String ruta = usuario.getRutaFoto();

        if (ruta == null || ruta.isBlank()) {
            lblFoto.setText("Sin fotografía");
            return;
        }

        File archivo = new File(ruta);

        if (!archivo.isFile()) {
            lblFoto.setText("Fotografía no disponible");
            return;
        }

        ImageIcon imagen = new ImageIcon(ruta);

        if (imagen.getIconWidth() <= 0) {
            lblFoto.setText("No se pudo cargar la fotografía");
            return;
        }

        Image escalada = imagen.getImage().getScaledInstance(
                120,
                120,
                Image.SCALE_SMOOTH
        );

        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(escalada));
    }
}
