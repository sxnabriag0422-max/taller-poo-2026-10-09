package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Usuario u = new Usuario("CC", "1234567890", "Gabriel", "Andres", "Sanabria", "Lopez", "Clave123", "3001234567", "correo@ejemplo.com", "foto.png");
        System.out.println(u.getPrimerNombre());
    }
}