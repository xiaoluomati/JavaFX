package ability;

import role.Enemy;
import role.UserRole;

public class WarriorAbilityB extends Ability {
    public WarriorAbilityB(String name) {
        super(name);
        this.damage = 0;
        this.heal = 0;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        if(userRole.getLp() * 4 < userRole.getLpCeiling() && enemy.getLp() < enemy.getLpCeiling()){
            int lp = enemy.getLp();
            enemy.decreaseLp(lp);
            userRole.increaseLp(lp);
        }
    }
}
