package ability;

import role.Enemy;
import role.UserRole;

public class RogueAbilityB extends Ability {

    public RogueAbilityB(String name) {
        super(name);
        this.damage = 120;
        this.heal = 40;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        enemy.decreaseLp((int) (this.damage * userRole.getAbilityPower()));
        userRole.increaseLp((int) (this.heal * userRole.getAbilityPower()));
        enemy.setState(BloodState.getInstance());
        if (enemy.getLp() == 0){
            this.damage += 50;
        }
        this.damage += 2;
    }
}
