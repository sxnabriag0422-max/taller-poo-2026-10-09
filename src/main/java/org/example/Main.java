package org.example;

public class Main {
    public static void main(String[] args) {
        Usuario u = new Usuario("CC", "1234567890", "Gabriel ", "", "Sanabria ", "Lopez ", "Clave123", "3001234567", "correo@ejemplo.com", "foto.png");
        System.out.println(u.generarUsername());
        System.out.println(u.getNombreCompleto());

        System.out.println(Validaciones.esPasswordSegura("Clave")); // falsa
        System.out.println(Validaciones.esPasswordSegura("Clave1234")); // falsa
        System.out.println(Validaciones.esPasswordSegura("Clave123!")); // verdadera
        System.out.println(Validaciones.esPasswordSegura("Cl1!")); // falsa
        System.out.println(Validaciones.esPasswordSegura("clave123!")); // falsa
        System.out.println(Validaciones.esFotoValida("perfil.PNG")); // verdadera
        System.out.println(Validaciones.esFotoValida("perfil.gif")); //falsa
        System.out.println(Validaciones.esTelefonoValido("3227586960")); // verdadero
        System.out.println(Validaciones.esTelefonoValido("+573227586960")); // falsa
        System.out.println(Validaciones.esTelefonoValido("32275a")); //falsa

    }



}