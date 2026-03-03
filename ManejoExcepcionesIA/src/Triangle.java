public class Triangle extends GeometricObject {

    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public Triangle() {
        super();
    }

    public Triangle(double side1, double side2, double side3)
            throws IllegalTriangleException {

        validarLados(side1, side2, side3);

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(double side1, double side2, double side3,
                    String color, boolean filled)
            throws IllegalTriangleException {

        super(color, filled);

        validarLados(side1, side2, side3);

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    private void validarLados(double s1, double s2, double s3)
            throws IllegalTriangleException {

        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1) {
            throw new IllegalTriangleException(
                    "Los lados no cumplen la regla del triángulo."
            );
        }
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triángulo: side1 = " + side1 +
                " side2 = " + side2 +
                " side3 = " + side3;
    }
}