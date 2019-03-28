package role;

public class RogueBehavior implements Behavior {
    @Override
    public int damageEnemy(int attack) {
        return (int) (attack * 1.4);
    }

    @Override
    public int getEnemyDamage(int damage) {
        return (int) (damage * 0.9);
    }

    @Override
    public void setRoleBaseValue(UserRole userRole) {
        userRole.changeAbilityPower(0.1);
    }
}
