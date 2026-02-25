import java.util.Date;

public abstract class GeometricObject {
    private String color;
    private boolean filled;
    private Date dateCreated;

    public GeometricObject(){
        color = "White";
        filled = false;
        dateCreated = new Date();
    }

    public GeometricObject(String color, boolean filled){
        this.color = color.toUpperCase();
        this.filled = filled;
        dateCreated = new Date();
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public boolean isFilled(){
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public abstract String toString();
}
