package org.example;

public class Validaciones {

    public static boolean esPasswordSegura(String password) {
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneSimbolo = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (!Character.isLetterOrDigit(c)) {
                tieneSimbolo = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo && password.length() >= 8;
    }

    public static boolean esFotoValida(String ruta) {
        String minuscula = ruta.toLowerCase();
        return minuscula.endsWith(".png") || minuscula.endsWith(".jpg") || minuscula.endsWith(".jpeg");
    }

    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.isEmpty()) return false;
        for (int i = 0; i < telefono.length(); i++) {
            char c = telefono.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}


