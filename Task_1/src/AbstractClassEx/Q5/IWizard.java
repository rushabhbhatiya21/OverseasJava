package AbstractClassEx.Q5;

public interface IWizard {
    public default void attack() {
        System.out.println("Attacking with Wizard's spells");
    }

    public void attackSkills();
}
