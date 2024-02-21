package AbstractClassEx.Q1;

public class Rectangle extends Shape {
    private final double width, height;

    public Rectangle(double w, double h) {
        this.width = w;
        this.height = h;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimiter() {
        return (width + height) * 2;
    }
}
