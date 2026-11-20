
package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class GeneradorPDF {

    private static final PDType1Font FUENTE =
            new PDType1Font(Standard14Fonts.FontName.HELVETICA);

    private static final PDType1Font NEGRITA =
            new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

    public static void generar(Usuario usuario, JFrame ventana) {

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar ficha del usuario");
        selector.setSelectedFile(
                new File("BookUsers_" + usuario.getId() + ".pdf")
        );

        int resultado = selector.showSaveDialog(ventana);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File destino = selector.getSelectedFile();

        if (!destino.getName().toLowerCase().endsWith(".pdf")) {
            destino = new File(destino.getAbsolutePath() + ".pdf");
        }

        if (destino.exists()) {
            int confirmar = JOptionPane.showConfirmDialog(
                    ventana,
                    "El archivo ya existe. ¿Deseas reemplazarlo?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            crearDocumento(usuario, destino);

            JOptionPane.showMessageDialog(
                    ventana,
                    "PDF generado correctamente:\n"
                            + destino.getAbsolutePath(),
                    "BookUsers",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    ventana,
                    "No se pudo generar el PDF:\n"
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void crearDocumento(
            Usuario usuario,
            File destino
    ) throws IOException {

        try (PDDocument documento = new PDDocument()) {

            PDPage pagina = new PDPage(PDRectangle.A4);
            documento.addPage(pagina);

            try (PDPageContentStream contenido =
                         new PDPageContentStream(documento, pagina)) {

                float y = 790;

                escribir(contenido, "BOOKUSERS",
                        50, y, 22, NEGRITA);

                y -= 28;

                escribir(contenido, "Ficha de usuario registrado",
                        50, y, 12, FUENTE);

                y -= 25;

                contenido.setStrokingColor(60 / 255f, 90 / 255f, 120 / 255f);                contenido.setLineWidth(1.5f);
                contenido.moveTo(50, y);
                contenido.lineTo(545, y);
                contenido.stroke();

                y -= 35;

                String rutaFoto = usuario.getRutaFoto();

                if (rutaFoto != null && !rutaFoto.isBlank()) {
                    File archivoFoto = new File(rutaFoto);

                    if (archivoFoto.isFile()) {
                        BufferedImage imagen =
                                ImageIO.read(archivoFoto);

                        if (imagen != null) {
                            PDImageXObject foto =
                                    LosslessFactory.createFromImage(
                                            documento, imagen
                                    );

                            float ancho = imagen.getWidth();
                            float alto = imagen.getHeight();

                            float escala = Math.min(
                                    120f / ancho,
                                    120f / alto
                            );

                            float anchoFinal = ancho * escala;
                            float altoFinal = alto * escala;

                            contenido.drawImage(
                                    foto,
                                    545 - anchoFinal,
                                    y - 120,
                                    anchoFinal,
                                    altoFinal
                            );
                        }
                    }
                }

                escribir(contenido, "INFORMACION PERSONAL",
                        50, y, 14, NEGRITA);

                y -= 28;

                List<String> datos = new ArrayList<>();

                datos.add("ID: " + usuario.getId());
                datos.add("Tipo de identificacion: "
                        + usuario.getTipoIdentificacion());
                datos.add("Numero de identificacion: "
                        + usuario.getNumeroIdentificacion());
                datos.add("Primer nombre: "
                        + usuario.getPrimerNombre());
                datos.add("Segundo nombre: "
                        + usuario.getSegundoNombre());
                datos.add("Primer apellido: "
                        + usuario.getPrimerApellido());
                datos.add("Segundo apellido: "
                        + usuario.getSegundoApellido());
                datos.add("Nombre completo: "
                        + usuario.getNombreCompleto());
                datos.add("Username: "
                        + usuario.getUsername());
                datos.add("Telefono: "
                        + usuario.getTelefono());
                datos.add("Correo: "
                        + usuario.getCorreo());

                for (String dato : datos) {
                    escribir(contenido, dato,
                            50, y, 11, FUENTE);
                    y -= 27;
                }

                y -= 20;

                contenido.setStrokingColor(180 / 255f, 180 / 255f, 180 / 255f);
                contenido.moveTo(50, y);
                contenido.lineTo(545, y);
                contenido.stroke();

                y -= 25;

                escribir(contenido,
                        "Documento generado por BookUsers",
                        50, y, 10, FUENTE);
            }

            documento.save(destino);
        }
    }

    private static void escribir(
            PDPageContentStream contenido,
            String texto,
            float x,
            float y,
            int tamano,
            PDType1Font fuente
    ) throws IOException {

        if (texto == null) {
            texto = "";
        }

        // Las fuentes estandar de PDF requieren
        // convertir caracteres no compatibles.
        texto = Normalizer.normalize(
                texto, Normalizer.Form.NFD
        );

        texto = texto.replaceAll("\\p{M}", "");
        texto = texto.replace('ñ', 'n')
                .replace('Ñ', 'N');

        texto = texto.replaceAll("[^\\x20-\\x7E]", "?");

        contenido.beginText();
        contenido.setFont(fuente, tamano);
        contenido.newLineAtOffset(x, y);
        contenido.showText(texto);
        contenido.endText();
    }
}
