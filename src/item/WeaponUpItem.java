package item;

import role.UserRole;

public class WeaponUpItem implements UpgradeItem {

    private int upAttack;

    public WeaponUpItem(int upAttack) {
        this.upAttack = upAttack;
    }

    @Override
    public String takeEffect(UserRole userRole) {
        userRole.upgradeWeapon(this.upAttack);
        return "当前装备武器攻击力增加了" + upAttack;
    }

    @Override
    public UpgradeItemType getType() {
        return UpgradeItemType.WATTACK;
    }
}
