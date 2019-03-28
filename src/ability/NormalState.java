package ability;

import role.Enemy;

public class NormalState implements AbilityState {

    private static NormalState ourInstance = new NormalState();

    public static NormalState getInstance() {
        return ourInstance;
    }

    private NormalState() {}

    @Override
    public void takeEffect(Enemy enemy) {

    }

    @Override
    public void dropEffect(Enemy enemy) {

    }
}
