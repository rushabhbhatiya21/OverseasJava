package AbstractClassEx.Q5;

public interface IWarrior {
    public default void attack() {
        System.out.println("Attacking with Warrior's sword.");
    }

    public void defenseSkills();
}
