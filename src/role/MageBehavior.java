package role;

public class MageBehavior implements Behavior {

    @Override
    public int damageEnemy(int attack) {
        return (int) (attack * 1.2);
    }

    @Override
    public int getEnemyDamage(int damage) {
        return damage;
    }

    @Override
    public void setRoleBaseValue(UserRole userRole) {
        userRole.changeAbilityPower(0.3);
    }
}
