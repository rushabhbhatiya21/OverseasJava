package AbstractClassEx.Q3;

public class Dog extends Animal{
    String name;

    Dog(String name) {
        this.name = name;
    }
    void display() {
        System.out.printf("I am a %s and I like to eat dog food.\n", name);
    }
    public void makeSound() {
        System.out.println("woof woof!");
    }

    @Override
    public void eat() {
        System.out.println("eating dog food...");
    }
}
