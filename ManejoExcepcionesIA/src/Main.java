import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("\n===== Crear Triángulo =====");

            System.out.print("Lado 1: ");
            double side1 = sc.nextDouble();

            System.out.print("Lado 2: ");
            double side2 = sc.nextDouble();

            System.out.print("Lado 3: ");
            double side3 = sc.nextDouble();
            sc.nextLine();

            System.out.print("Color: ");
            String color = sc.nextLine();

            System.out.print("¿Está relleno? (true/false): ");
            boolean filled = sc.nextBoolean();

            Triangle triangle =
                    new Triangle(side1, side2, side3, color, filled);

            System.out.println("\n" + triangle);
            System.out.println("Área: " + triangle.getArea());
            System.out.println("Perímetro: " + triangle.getPerimeter());
            System.out.println("Color: " + triangle.getColor());
            System.out.println("Relleno: " + triangle.isFilled());

        } catch (IllegalTriangleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
        }

        sc.close();
    }
}