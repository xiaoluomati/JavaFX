package role;

public class WarriorBehavior implements Behavior {
    @Override
    public int damageEnemy(int attack) {
        return attack;
    }

    @Override
    public int getEnemyDamage(int damage) {
        return (int) (damage * 0.6);
    }

    @Override
    public void setRoleBaseValue(UserRole userRole) {
        userRole.addLpc((int) (userRole.getLpCeiling() * 0.3));
    }
}
