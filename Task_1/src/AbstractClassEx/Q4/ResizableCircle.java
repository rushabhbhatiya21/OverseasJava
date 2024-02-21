package AbstractClassEx.Q4;

public class ResizableCircle extends Shape implements IResizable{
    private double radius;

    public ResizableCircle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimiter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double resize(double newRadius) {
        this.radius = newRadius;
        return this.radius;
    }
}
