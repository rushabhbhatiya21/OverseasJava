package AbstractClassEx.Q5;

public class Witcher implements IWarrior, IWizard {
    @Override
    public void attack() {
        IWarrior.super.attack();
        IWizard.super.attack();
    }

    @Override
    public void defenseSkills() {
        System.out.println("I got defense skills of an warrior.");
    }

    @Override
    public void attackSkills() {
        System.out.println("I got attack skills of an wizard.");
    }
}
