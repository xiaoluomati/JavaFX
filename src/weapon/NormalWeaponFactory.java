package weapon;

import util.WeaponItemUtil;

public class NormalWeaponFactory extends WeaponFactory {
    public NormalWeaponFactory(int itemLevel) {
        super(itemLevel);
    }

    @Override
    public Weapon createWeapon(WeaponType weapontype) {
        switch (weapontype){
            case ABILITY:
                return new AbilityWeapon("normalAbilityWeapon",
                        WeaponItemUtil.WEAPON_DEFAULT_AP * upgradeLevel(),
                        (int) (WeaponItemUtil.WEAPON_DEFAULT_ATTACK * upgradeLevel()));
            default:
                Weapon normalAttackWeapon = new AttackWeapon("normalAttackWeapon");
                normalAttackWeapon.setAttack((int) (WeaponItemUtil.WEAPON_DEFAULT_ATTACK * upgradeLevel()));
                return normalAttackWeapon;
        }
    }
}
