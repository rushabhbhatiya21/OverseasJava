package AbstractClassEx.Q4;

import AbstractClassEx.Q1.Circle;
import AbstractClassEx.Q1.Rectangle;

public class Main {
    public static void main(String[] args) {
        Circle normalCircle = new Circle(5);
        double circleArea = normalCircle.calculateArea();
        System.out.printf("Area of normalCircle: %.2f\n", circleArea);

        ResizableCircle resizableCircle = new ResizableCircle(5);
        double resizableCircleArea = resizableCircle.calculateArea();
        System.out.printf("Area of Resizable Circle of original Radius: %.2f\n", resizableCircleArea);

        double newRadius = resizableCircle.resize(10);
        System.out.printf("Area of Resizable Circle of after changing Radius to %.2f: %.2f\n", newRadius, resizableCircle.calculateArea());
    }
}

