package ability;

import role.Enemy;
import role.UserRole;

public class MageAbilityB extends Ability {

    public MageAbilityB(String name) {
        super(name);
        this.damage = 200;
        this.heal = 50;
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        double userAP = userRole.getAbilityPower();
        if(enemy.getState().equals(FrozenState.getInstance())){
            userAP += 0.25;
        }
        enemy.decreaseLp((int) (this.damage * userAP));
        enemy.setState(FrozenState.getInstance());
    }

}
