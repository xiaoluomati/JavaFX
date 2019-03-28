package ability;

import role.Enemy;
import role.UserRole;

public class MageAbilityA extends Ability {


    public MageAbilityA(String name) {
        super(name);
        this.damage = 100;
        this.heal = 50;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        enemy.decreaseLp((int) (this.damage * userRole.getAbilityPower()));
        userRole.increaseLp((int) (this.heal * userRole.getAbilityPower()));
        enemy.setState(FrozenState.getInstance());
    }


}
