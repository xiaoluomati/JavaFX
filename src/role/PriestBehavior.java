package role;

public class PriestBehavior implements Behavior {

    @Override
    public int damageEnemy(int attack) {
        return (int) (attack * 1.2);
    }

    @Override
    public int getEnemyDamage(int damage) {
        return (int) (damage * 0.8);
    }

    @Override
    public void setRoleBaseValue(UserRole userRole) {
        userRole.addLpc((int) (userRole.getLpCeiling() * 0.1));
    }
}
