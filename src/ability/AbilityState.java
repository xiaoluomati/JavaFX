package ability;

import role.Enemy;

public interface AbilityState {

    void takeEffect(Enemy enemy);

    void dropEffect(Enemy enemy);

}
