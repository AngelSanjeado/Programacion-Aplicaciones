import java.util.Scanner;

public class Main {

    public static void numeroImpar(int numero){
        if(numero % 2 != 0){
            throw new IllegalArgumentException("El número impar");
        }

        System.out.println("El número es par");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n==== Número impar ====");
        System.out.println("\nIngrese '0' para salir");
        int numero;

        do {

            System.out.print("\nIntroduce un número: ");
            numero = sc.nextInt();

            try {
                numeroImpar(numero);
            } catch (IllegalArgumentException e) {
                System.out.println("Oh no! " + e.getMessage());
            }

        }while (numero != 0);
    }
}