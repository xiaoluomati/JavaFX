package item;

import util.WeaponItemUtil;

public class RoleUpItemFactory extends UpgradeItemFactory {


    public RoleUpItemFactory(int itemLevel) {
        super(itemLevel);
    }

    @Override
    public UpgradeItem createUpgradeItem(UpgradeItemType type) {
        switch (type){
            case AP:
                return new APUpgradeItem(WeaponItemUtil.ITEM_AP_UPGRADE * upgradeLevel());
            case LP:
                return new LPUpgradeItem((int) (WeaponItemUtil.ITEM_LP_UPGRADE * upgradeLevel()));
            case LPC:
                return new LPCUpgradeItem((int) (WeaponItemUtil.ITEM_LPC_UPGRADE * upgradeLevel()));
            case RATTACK:
                return new RoleAttackUpItem((int) (WeaponItemUtil.ITEM_ATTACK_UPGRADE * upgradeLevel()));
            case WATTACK:
                return new WeaponUpItem((int) (WeaponItemUtil.ITEM_WEAPON_ATTACK * upgradeLevel()));
        }
        return null;
    }
}
