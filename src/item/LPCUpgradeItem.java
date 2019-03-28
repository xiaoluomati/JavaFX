package item;

import role.UserRole;

public class LPCUpgradeItem implements UpgradeItem {

    private int lpc;

    public LPCUpgradeItem(int lpc) {
        this.lpc = lpc;
    }

    @Override
    public String takeEffect(UserRole userRole) {
        userRole.addLpc(lpc);
        return "生命值上限增加了" + lpc;
    }

    @Override
    public UpgradeItemType getType() {
        return UpgradeItemType.LPC;
    }
}
