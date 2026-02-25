public class Triangle extends GeometricObject{
    private double side1;
    private double side2;
    private double side3;

    public Triangle(){
        super();
        side1 = 1.0;
        side2 = 1.0;
        side3 = 1.0;
    }

    public Triangle(double side1, double side2, double side3, String color, boolean filled){
        super(color, filled);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(double side1, double side2, double side3) throws IllegalTriangleException{
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        try{
            validarTamanioLados(side1, side2, side3);
        } catch (IllegalTriangleException e){
            System.out.println("\n\nError: " + e.getMessage());
        }
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public double getArea(){
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter(){
        return side1 + side2 + side3;
    }

    public void validarTamanioLados(double side1, double side2, double side3) throws IllegalTriangleException {
        if ((side1 + side2) < side3){
            throw new IllegalTriangleException("El lado 3 es más grande que la suma de los otros dos lados");
        }

        if ((side1 + side3) < side2){
            throw new IllegalTriangleException("El lado 2 es más grande que la suma de los otros dos lados");
        }

        if ((side2 + side3) < side1){
            throw new IllegalTriangleException("El lado 1 es más grande que la suma de los otros dos lados");
        }
    }
    @Override
    public String toString() {
        return String.format("Triangulo\nside1 = %.2f\nside2 = %.2f\nside3 = %.2f", side1, side2, side3);
    }
}

