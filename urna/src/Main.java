import java.util.Scanner;
//Se requiere calcular el porcentaje de votos que están en una urna.
//Cada que se saca un boto de la urna, se ingresa el número del candidato.
//Cuando se terminan los votos se ingresa un cero

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos candidatos se postulan? ");
        int cantidad = sc.nextInt();

        int[] urna = new int[cantidad];
        int candidato;
        int totalVotos = 0;

        System.out.println("\nIngrese 0 para salir");

        do {
            System.out.print("Ingrese el número del candidato según corresponda: ");
            candidato = sc.nextInt();

            if (candidato == 0) break;

            try {
                urna[candidato - 1] += 1;
                totalVotos++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("El candidato no existe \nError: " + e.getMessage() + "\n");
            }

        }while(true);

        double porcentaje = 0.0;

        System.out.print("\n");

        for (int i = 0; i < urna.length; i++){

            if (totalVotos > 0){
                porcentaje = (urna[i] * 100.0) / totalVotos;
            }

            System.out.printf("Candidato %d: %.2f%%\n", i + 1, porcentaje);
            porcentaje = 0;
        }

    }
}