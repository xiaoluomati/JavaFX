package battle;

import ability.NormalState;
import role.Enemy;
import role.UserRole;

public class AttackCommand implements Command {

    private UserRole userRole;

    private Enemy enemy;

    public AttackCommand(UserRole userRole, Enemy enemy) {
        this.userRole = userRole;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        int damage = userRole.getDamage();
        enemy.decreaseLp(damage);
        enemy.setState(NormalState.getInstance());
    }
}
