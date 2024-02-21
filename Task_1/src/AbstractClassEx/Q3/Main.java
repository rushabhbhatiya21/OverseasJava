package AbstractClassEx.Q3;

public class Main {
    public static void main(String[] args) {
        Dog husky = new Dog("husky");
        husky.display();
        husky.makeSound();

        Cat casper = new Cat("casper");
        casper.display();
        casper.eat();
    }
}
