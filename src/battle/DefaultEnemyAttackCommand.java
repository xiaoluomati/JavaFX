package battle;

import role.Enemy;
import role.UserRole;

public class DefaultEnemyAttackCommand implements Command {

    private UserRole userRole;

    private Enemy enemy;

    public DefaultEnemyAttackCommand(UserRole userRole, Enemy enemy) {
        this.userRole = userRole;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        this.userRole.decreaseLp(this.enemy.getDamage());
    }
}
