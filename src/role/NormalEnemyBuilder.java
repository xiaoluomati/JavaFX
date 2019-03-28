package role;

import item.RoleUpItemFactory;
import item.UpgradeItem;
import item.UpgradeItemFactory;
import item.UpgradeItemType;
import util.RoleUtil;
import weapon.NormalWeaponFactory;
import weapon.Weapon;
import weapon.WeaponFactory;
import weapon.WeaponType;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class NormalEnemyBuilder extends EnemyBuilder {


    public NormalEnemyBuilder(int level) {
        super(level);
    }

    @Override
    protected void addExp() {
        this.enemy.setExp((int) (RoleUtil.ENEMY_BASE_EXP * upgradeLevel()));
    }

    @Override
    protected void addBehavior() {
        this.enemy.setBehavior(new Behavior() {
            @Override
            public int damageEnemy(int attack) {
                return attack;
            }

            @Override
            public int getEnemyDamage(int damage) {
                return damage;
            }

            @Override
            public void setRoleBaseValue(UserRole userRole) {

            }
        });
    }

    @Override
    protected void addItems() {
        List<UpgradeItem> items = new Vector<>();
        Random random = new Random();
        int size = random.nextInt(2 * this.level + 1);
        UpgradeItemFactory upgradeItemFactory = new RoleUpItemFactory(this.level);
        for (int i = 0; i < size; i++) {
            items.add(upgradeItemFactory.createUpgradeItem(UpgradeItemType.getByInt(random.nextInt(6))));
        }
        this.enemy.setItems(items);
    }

    @Override
    protected void addWeapons() {
        List<Weapon> weapons = new Vector<>();
        Random random = new Random();
        int size = random.nextInt(2 * level);
        WeaponFactory weaponFactory = new NormalWeaponFactory(this.level);
        for (int i = 0; i < size; i++) {
            weapons.add(weaponFactory.createWeapon(WeaponType.getByInt(random.nextInt(2))));
        }
        this.enemy.setWeapons(weapons);
    }
}
