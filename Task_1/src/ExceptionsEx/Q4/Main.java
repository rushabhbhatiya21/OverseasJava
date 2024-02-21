package ExceptionsEx.Q4;

public class Main {
    public static void validate() {
        int a = 10, b = 0;
        int c = a / b;
    }

    void sendAge() {
        validate();
    }

    public void handleAgeException() {
        try {
            sendAge();
        }
        catch (Exception e) {
            System.out.println("Exception handled!");
        }
    }

    public static void main(String[] args) {
        Main handleAge = new Main();
        System.out.println("Start...");
        handleAge.handleAgeException();
        System.out.println("Continue...");
    }
}
