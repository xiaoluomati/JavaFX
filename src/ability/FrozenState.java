package ability;

import role.Enemy;

public class FrozenState implements AbilityState {
    private static FrozenState ourInstance = new FrozenState();

    public static FrozenState getInstance() {
        return ourInstance;
    }

    private FrozenState() {
    }

    @Override
    public void takeEffect(Enemy enemy) {
        enemy.setArmor(enemy.getArmor() - 50);
    }

    @Override
    public void dropEffect(Enemy enemy) {
        enemy.setArmor(enemy.getArmor() + 50);
    }
}
