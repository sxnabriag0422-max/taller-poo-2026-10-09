package org.example;

public class Main {
    public static void main(String[] args) {
        // Pruebas del username (según los ejemplos de la guía)
        System.out.println(new Usuario("13", "1", "Leidy", "", "Niño", "Castañeda", "x", "3", "a@b.com", "f.png").getUsername());   // ncleidy
        System.out.println(new Usuario("13", "2", "Ariadna", "", "Niño", "", "x", "3", "a@b.com", "f.png").getUsername());         // nariadna
        System.out.println(new Usuario("13", "3", "Juan", "", "Pérez", "Ñústez", "x", "3", "a@b.com", "f.png").getUsername());     // pnjuan

        // Prueba del nombre completo
        Usuario u = new Usuario("CC", "1234567890", "Gabriel", "", "Sanabria", "Lopez", "Clave123", "3001234567", "correo@ejemplo.com", "foto.png");
        System.out.println(u.getNombreCompleto());   // Gabriel Sanabria Lopez

        // Pruebas de validaciones
        System.out.println(Validaciones.esPasswordSegura("Clave"));          // false
        System.out.println(Validaciones.esPasswordSegura("Clave1234"));      // false
        System.out.println(Validaciones.esPasswordSegura("Clave123!"));      // true
        System.out.println(Validaciones.esPasswordSegura("Cl1!"));           // false
        System.out.println(Validaciones.esPasswordSegura("clave123!"));      // false
        System.out.println(Validaciones.esFotoValida("perfil.PNG"));         // true
        System.out.println(Validaciones.esFotoValida("perfil.gif"));         // false
        System.out.println(Validaciones.esTelefonoValido("3227586960"));     // true
        System.out.println(Validaciones.esTelefonoValido("+573227586960"));  // false
        System.out.println(Validaciones.esTelefonoValido("32275a"));         // false
    }
}