import java.util.Scanner;

public class Main {

    public static void numeroImpar(int numero) throws Exception{
        if(numero % 2 != 0){
            throw new Exception("El número impar");
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

//            if ((char) numero == 'e') break;

            try {
                numeroImpar(numero);
            } catch (Exception e) {
                System.out.println("Oh no! " + e.getMessage());
            }

        }while (numero != 0);
    }
}