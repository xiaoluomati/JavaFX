package battle;

import role.Enemy;
import role.UserRole;

public class Battle {

    private UserRole userRole;

    private Enemy enemy;

    private Command defaultEnemyAttackCommand;

    private boolean isEnd = false;

    public Battle(UserRole userRole, Enemy enemy) {
        this.userRole = userRole;
        this.enemy = enemy;
        this.defaultEnemyAttackCommand = new DefaultEnemyAttackCommand(userRole, enemy);
    }

    public boolean addCommand(Command command){
        this.invokerCommand(command);
        if(!isEnd){
            this.invokerCommand(defaultEnemyAttackCommand);
        }
        return isEnd;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void invokerCommand(Command command){
        command.execute();
        if(userRole.getLp() == 0 || enemy.getLp() == 0){
            this.isEnd = true;
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
