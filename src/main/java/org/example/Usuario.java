package org.example;

import java.text.Normalizer;

public class Usuario {
    private int id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String username;
    private String password;
    private String telefono;
    private String correo;
    private String rutaFoto;

    public Usuario(String tipoIdentificacion, String numeroIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String password, String telefono, String correo, String rutaFoto) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.rutaFoto = rutaFoto;
        this.username = generarUsername();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public String getNombreCompleto() {
        String nombreSegundo = "";
        if (!segundoNombre.trim().isEmpty()) {
            nombreSegundo = " " + segundoNombre.trim();
        }
        String apellidoSegundo = "";
        if (!segundoApellido.trim().isEmpty()) {
            apellidoSegundo = " " + segundoApellido.trim();
        }
        return primerNombre.trim() + nombreSegundo + " " + primerApellido.trim() + apellidoSegundo;
    }

    public String generarUsername() {
        return construirUsername(primerNombre, primerApellido, segundoApellido);
    }

    // Estático para poder usarlo en el formulario antes de crear el usuario
    public static String construirUsername(String primerNombre, String primerApellido, String segundoApellido) {
        String inicialPrimero = "";
        if (!primerApellido.trim().isEmpty()) {
            inicialPrimero = primerApellido.trim().substring(0, 1);
        }
        String inicialSegundo = "";
        if (!segundoApellido.trim().isEmpty()) {
            inicialSegundo = segundoApellido.trim().substring(0, 1);
        }
        String texto = inicialPrimero + inicialSegundo + primerNombre.trim();
        return limpiar(texto);
    }

    // Minúsculas, sin tildes, sin ñ y sin espacios
    private static String limpiar(String texto) {
        String separado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        String sinTildes = separado.replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().replace(" ", "");
    }

    @Override
    public String toString() {
        return id + " - " + getNombreCompleto() + " - " + username;
    }
}