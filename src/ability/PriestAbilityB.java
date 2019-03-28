package ability;

import role.Enemy;
import role.UserRole;

public class PriestAbilityB extends Ability {

    public PriestAbilityB(String name) {
        super(name);
        this.damage = 100;
        this.heal = 50;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        double abilityPower = userRole.getAbilityPower();
        enemy.decreaseLp((int) (this.damage * abilityPower));
        if(enemy.getState().equals(LightState.getInstance())){
            abilityPower *= 2;
        }
        userRole.increaseLp((int) (this.heal * abilityPower));
        enemy.setState(LightState.getInstance());
    }
}
