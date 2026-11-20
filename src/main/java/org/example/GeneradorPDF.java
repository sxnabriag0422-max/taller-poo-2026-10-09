
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeneradorPDF {

    private static final PDType1Font FUENTE =
            new PDType1Font(
                    Standard14Fonts.FontName.HELVETICA
            );

    private static final PDType1Font NEGRITA =
            new PDType1Font(
                    Standard14Fonts.FontName.HELVETICA_BOLD
            );

    private static final float MARGEN = 50;
    private static final float ANCHO_FOTO = 120;
    private static final float ALTO_FOTO = 120;
    private static final float INTERLINEADO = 16;

    public static void generar(
            Usuario usuario,
            JFrame ventana
    ) {

        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle(
                "Guardar ficha del usuario"
        );

        selector.setSelectedFile(
                new File(
                        "BookUsers_" + usuario.getId() + ".pdf"
                )
        );

        int resultado =
                selector.showSaveDialog(ventana);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File destino = selector.getSelectedFile();

        if (!destino.getName()
                .toLowerCase(Locale.ROOT)
                .endsWith(".pdf")) {

            destino = new File(
                    destino.getAbsolutePath() + ".pdf"
            );
        }

        if (destino.exists()) {

            int confirmar =
                    JOptionPane.showConfirmDialog(
                            ventana,
                            "El archivo ya existe.\n"
                                    + "¿Deseas reemplazarlo?",
                            "Confirmar reemplazo",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
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
                    "Error al generar PDF",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void crearDocumento(
            Usuario usuario,
            File destino
    ) throws IOException {

        try (PDDocument documento = new PDDocument()) {

            PDPage pagina =
                    new PDPage(PDRectangle.A4);

            documento.addPage(pagina);

            float anchoPagina =
                    pagina.getMediaBox().getWidth();

            float altoPagina =
                    pagina.getMediaBox().getHeight();

            float limiteDerecho =
                    anchoPagina - MARGEN;

            try (PDPageContentStream contenido =
                         new PDPageContentStream(
                                 documento,
                                 pagina
                         )) {

                float y = altoPagina - MARGEN;

                // ENCABEZADO
                escribir(
                        contenido,
                        "BOOKUSERS",
                        MARGEN,
                        y,
                        22,
                        NEGRITA
                );

                y -= 28;

                escribir(
                        contenido,
                        "Ficha de usuario registrado",
                        MARGEN,
                        y,
                        12,
                        FUENTE
                );

                y -= 25;

                contenido.setStrokingColor(
                        60 / 255f,
                        90 / 255f,
                        120 / 255f
                );

                contenido.setLineWidth(1.5f);

                contenido.moveTo(MARGEN, y);
                contenido.lineTo(limiteDerecho, y);
                contenido.stroke();

                y -= 35;

                // FOTOGRAFÍA
                float posicionFotoY = y - ALTO_FOTO;

                dibujarFoto(
                        documento,
                        contenido,
                        usuario.getRutaFoto(),
                        limiteDerecho - ANCHO_FOTO,
                        posicionFotoY
                );

                // TÍTULO DE LA SECCIÓN
                escribir(
                        contenido,
                        "INFORMACIÓN PERSONAL",
                        MARGEN,
                        y,
                        14,
                        NEGRITA
                );

                y -= 28;

                // DATOS DEL USUARIO
                List<String> datos = new ArrayList<>();

                datos.add(
                        "ID: " + usuario.getId()
                );

                datos.add(
                        "Tipo de identificación: "
                                + usuario.getTipoIdentificacion()
                );

                datos.add(
                        "Número de identificación: "
                                + usuario.getNumeroIdentificacion()
                );

                datos.add(
                        "Primer nombre: "
                                + usuario.getPrimerNombre()
                );

                datos.add(
                        "Segundo nombre: "
                                + usuario.getSegundoNombre()
                );

                datos.add(
                        "Primer apellido: "
                                + usuario.getPrimerApellido()
                );

                datos.add(
                        "Segundo apellido: "
                                + usuario.getSegundoApellido()
                );

                datos.add(
                        "Nombre completo: "
                                + usuario.getNombreCompleto()
                );

                datos.add(
                        "Username: "
                                + usuario.getUsername()
                );

                datos.add(
                        "Teléfono: "
                                + usuario.getTelefono()
                );

                datos.add(
                        "Correo: "
                                + usuario.getCorreo()
                );

                // Reservar espacio para la foto.
                // Los primeros datos usan un ancho menor.
                int indice = 0;

                for (String dato : datos) {

                    float anchoDisponible;

                    if (indice < 5) {
                        anchoDisponible =
                                anchoPagina
                                        - (MARGEN * 2)
                                        - ANCHO_FOTO
                                        - 20;
                    } else {
                        anchoDisponible =
                                anchoPagina
                                        - (MARGEN * 2);
                    }

                    List<String> lineas =
                            dividirTexto(
                                    dato,
                                    FUENTE,
                                    11,
                                    anchoDisponible
                            );

                    for (String linea : lineas) {

                        escribir(
                                contenido,
                                linea,
                                MARGEN,
                                y,
                                11,
                                FUENTE
                        );

                        y -= INTERLINEADO;
                    }

                    y -= 10;
                    indice++;
                }

                // PIE DEL DOCUMENTO
                y -= 15;

                contenido.setStrokingColor(
                        180 / 255f,
                        180 / 255f,
                        180 / 255f
                );

                contenido.setLineWidth(1);

                contenido.moveTo(MARGEN, y);
                contenido.lineTo(limiteDerecho, y);
                contenido.stroke();

                y -= 25;

                escribir(
                        contenido,
                        "Documento generado por BookUsers",
                        MARGEN,
                        y,
                        10,
                        FUENTE
                );
            }

            documento.save(destino);
        }
    }

    private static void dibujarFoto(
            PDDocument documento,
            PDPageContentStream contenido,
            String rutaFoto,
            float x,
            float y
    ) throws IOException {

        if (rutaFoto == null
                || rutaFoto.isBlank()) {
            return;
        }

        File archivoFoto = new File(rutaFoto);

        if (!archivoFoto.isFile()) {
            return;
        }

        BufferedImage imagen =
                ImageIO.read(archivoFoto);

        if (imagen == null) {
            return;
        }

        PDImageXObject foto =
                LosslessFactory.createFromImage(
                        documento,
                        imagen
                );

        float anchoOriginal =
                imagen.getWidth();

        float altoOriginal =
                imagen.getHeight();

        float escala = Math.min(
                ANCHO_FOTO / anchoOriginal,
                ALTO_FOTO / altoOriginal
        );

        float anchoFinal =
                anchoOriginal * escala;

        float altoFinal =
                altoOriginal * escala;

        float xCentrada =
                x + (ANCHO_FOTO - anchoFinal) / 2;

        float yCentrada =
                y + (ALTO_FOTO - altoFinal) / 2;

        contenido.drawImage(
                foto,
                xCentrada,
                yCentrada,
                anchoFinal,
                altoFinal
        );
    }

    private static List<String> dividirTexto(
            String texto,
            PDType1Font fuente,
            int tamano,
            float anchoMaximo
    ) throws IOException {

        List<String> lineas =
                new ArrayList<>();

        if (texto == null || texto.isBlank()) {
            lineas.add("");
            return lineas;
        }

        String[] palabras =
                texto.trim().split("\\s+");

        StringBuilder lineaActual =
                new StringBuilder();

        for (String palabra : palabras) {

            String candidata =
                    lineaActual.isEmpty()
                            ? palabra
                            : lineaActual + " " + palabra;

            float ancho =
                    fuente.getStringWidth(candidata)
                            / 1000f * tamano;

            if (ancho <= anchoMaximo) {

                lineaActual =
                        new StringBuilder(candidata);

            } else {

                if (!lineaActual.isEmpty()) {
                    lineas.add(
                            lineaActual.toString()
                    );
                }

                lineaActual =
                        new StringBuilder(palabra);
            }
        }

        if (!lineaActual.isEmpty()) {
            lineas.add(
                    lineaActual.toString()
            );
        }

        return lineas;
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

        // Helvetica estándar permite los caracteres
        // habituales del español mediante WinAnsi.
        contenido.beginText();

        contenido.setFont(
                fuente,
                tamano
        );

        contenido.newLineAtOffset(x, y);
        contenido.showText(texto);
        contenido.endText();
    }
}
