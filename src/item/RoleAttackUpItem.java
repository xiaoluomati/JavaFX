package item;

import role.UserRole;

public class RoleAttackUpItem implements UpgradeItem {

    private int attack;

    public RoleAttackUpItem(int attack) {
        this.attack = attack;
    }

    @Override
    public String takeEffect(UserRole userRole) {
        userRole.addBaseAttack(attack);
        return "人物基础攻击力增加了" + attack;
    }

    @Override
    public UpgradeItemType getType() {
        return UpgradeItemType.RATTACK;
    }
}
