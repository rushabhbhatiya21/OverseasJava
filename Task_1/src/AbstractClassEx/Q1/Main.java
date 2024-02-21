package AbstractClassEx.Q1;

public class Main {
    public static void main(String[] args) {
        Circle c = new Circle(5);
        double circleArea = c.calculateArea();
        System.out.printf("Area of c: %.2f\n", circleArea);

        Rectangle r = new Rectangle(5, 10);
        double rectPer = r.calculatePerimiter();
        System.out.printf("Perimeter of rectangle: %.2f", rectPer);
    }
}

