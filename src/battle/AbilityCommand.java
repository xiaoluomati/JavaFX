package battle;

import ability.Ability;
import role.Enemy;
import role.UserRole;

public class AbilityCommand implements Command {

    private UserRole userRole;

    private Enemy enemy;

    private Ability ability;

    public AbilityCommand(UserRole userRole, Enemy enemy, Ability ability) {
        this.userRole = userRole;
        this.enemy = enemy;
        this.ability = ability;
    }

    @Override
    public void execute() {
        this.ability.takeEffect(userRole, enemy);
    }
}
