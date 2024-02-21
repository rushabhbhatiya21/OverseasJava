package AbstractClassEx.Q4;

import AbstractClassEx.Q1.Shape;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
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
}
