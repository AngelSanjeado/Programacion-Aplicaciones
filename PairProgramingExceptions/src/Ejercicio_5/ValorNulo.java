package Ejercicio_5;

import Ejercicio3_4.NumeroNegativoException;
import Ejercicio3_4.PruebaNumeroNegativo;

public class Main {
    // Método que puede lanzar la excepción
    public void verificarNumero(int numero) throws NumeroNegativoException {

        if (numero < 0) {
            throw new NumeroNegativoException("El número no puede ser negativo.");
        }

        System.out.println("El número es válido: " + numero);
    }

    public static void main(String[] args) {

        PruebaNumeroNegativo obj = null;

        try {
            obj.verificarNumero(5);// Aquí la excepción

        } catch (Exception e) {

            System.out.println("Se capturó la excepción:");
            System.out.println(e.getMessage());
        }
    }
}
