import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalTriangleException {
        System.out.println("\n===== Crea un triangulo =====\n");
        System.out.println("Introduce la medida de los tres lados de triangulo");
        Scanner sc = new Scanner(System.in);

        System.out.print("Lado 1: ");
        double side1 = sc.nextDouble();
        sc.nextLine();

        System.out.print("Lado 2: ");
        double side2 = sc.nextDouble();
        sc.nextLine();

        System.out.print("Lado 3: ");
        double side3 = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nIntroduce de que color es tu figura");
        System.out.print("Color: ");
        String color = sc.nextLine();

        System.out.println("\n¿La figura esta rellena?\nIntroduzca 1 para 'Si' y 0 para 'No'");
        System.out.print("Respuesta: ");
        int respuesta = sc.nextInt();
        boolean relleno = respuesta == 1;

        Triangle triangle = new Triangle(side1, side2, side3, color, relleno);

        System.out.printf("\nArea: %.2f \nPerimetro: %.2f \nColor: %s \nRelleno: %b", triangle.getArea(), triangle.getPerimeter(), triangle.getColor(), triangle.isFilled());

        Triangle triangle2 = new Triangle(1, 2, 9);
        System.out.println(triangle2.toString());
    }
}
