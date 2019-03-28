package ability;

import role.Enemy;
import role.UserRole;

public class PriestAbilityA extends Ability {

    public PriestAbilityA(String name) {
        super(name);
        this.damage = 50;
        this.heal = 100;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        enemy.decreaseLp((int) (this.damage * userRole.getAbilityPower()));
        userRole.increaseLp((int) (this.heal * userRole.getAbilityPower()));
        enemy.setState(LightState.getInstance());
    }
}
