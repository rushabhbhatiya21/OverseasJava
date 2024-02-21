package AbstractClassEx.Q3;

public class Cat extends Animal{
    String name;

    Cat(String name) {
        this.name = name;
    }

    void display() {
        System.out.printf("I am a %s and I like to annoy people.\n", name);
    }
    @Override
    public void makeSound() {
        System.out.println("meaowwwww!");
    }

    @Override
    public void eat() {
        System.out.println("eating cat food...");
    }
}
