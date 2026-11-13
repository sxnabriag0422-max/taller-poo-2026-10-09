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
}