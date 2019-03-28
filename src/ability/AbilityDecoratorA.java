package ability;

import role.Enemy;
import role.UserRole;

public class AbilityDecoratorA extends AbilityDecorator {


    public AbilityDecoratorA(Ability ability) {
        super(ability);
        this.name = ability.name;
        this.damage = (int) (ability.damage * 1.05);
        this.heal = (int) (ability.heal * 1.05);
    }

    @Override
    public void takeEffect(UserRole userRole, Enemy enemy) {
        ability.takeEffect(userRole, enemy);
    }




}
