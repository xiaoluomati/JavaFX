package ability;

import role.Enemy;

public class LightState  implements AbilityState{
    private static LightState ourInstance = new LightState();

    public static LightState getInstance() {
        return ourInstance;
    }

    private LightState() {
    }

    @Override
    public void takeEffect(Enemy enemy) {
        enemy.setAttack((int) (enemy.getAttack() * 0.5));
    }

    @Override
    public void dropEffect(Enemy enemy) {
        enemy.setAttack(enemy.getAttack() * 2);
    }
}
