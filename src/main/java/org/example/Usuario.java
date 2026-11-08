package org.example;

public class Usuario{
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
    private String foto;

    public Usuario(String tipoIdentificacion, String numeroIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String password, String telefono, String correo, String foto) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = foto;
    }
    public String getPrimerNombre() {
        return primerNombre;
    }
}

