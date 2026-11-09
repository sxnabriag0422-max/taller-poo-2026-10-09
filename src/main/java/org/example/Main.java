package org.example;

public class Main {
    public static void main(String[] args) {
        Usuario u = new Usuario("CC", "1234567890", "Gabriel ", "", "Sanabria ", "Lopez ", "Clave123", "3001234567", "correo@ejemplo.com", "foto.png");
        System.out.println(u.generarUsername());
        System.out.println(u.getNombreCompleto());

        System.out.println(Validaciones.esPasswordSegura("Clave"));
        System.out.println(Validaciones.esPasswordSegura("Clave1234"));





    }



}