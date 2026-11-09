package org.example;

public class Validaciones {

    public static boolean esPasswordSegura(String password) {
        boolean tieneMayuscula = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
        }

        return tieneMayuscula && password.length() >= 8;

    }

}
