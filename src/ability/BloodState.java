package ability;

import role.Enemy;

import java.util.Random;

public class BloodState implements AbilityState {
    private static BloodState ourInstance = new BloodState();

    public static BloodState getInstance() {
        return ourInstance;
    }

    private BloodState() {
    }


    @Override
    public void takeEffect(Enemy enemy) {
        Random random = new Random();
        int i = random.nextInt(10);
        if(i == 0){
            enemy.decreaseLp(enemy.getLp());
        }
    }

    @Override
    public void dropEffect(Enemy enemy) {

    }
}
