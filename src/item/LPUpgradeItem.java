package item;

import role.UserRole;

public class LPUpgradeItem implements UpgradeItem {

    private int lp;

    public LPUpgradeItem(int lp) {
        this.lp = lp;
    }

    @Override
    public String takeEffect(UserRole userRole) {
        userRole.increaseLp(lp);
        return "血量回复了" + lp;
    }

    @Override
    public UpgradeItemType getType() {
        return UpgradeItemType.LP;
    }


}
