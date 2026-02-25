public class Circle extends GeometricObject{
    private double radius;

    public Circle(){
        super();
        radius = 1.0;
    }

    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius){
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return 1.0;
    }

    public double getPerimeter(){
        return 1.0;
    }

    public double getDiameter(){
        return 1.0;
    }

    public void printCircle(){

    }

    @Override
    public String toString(){
        return String.format("%s\t%b\t%.2f", getColor(), isFilled(), getRadius());
    }
}
