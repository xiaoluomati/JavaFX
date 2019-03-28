package item;

import role.UserRole;
import util.FormatUtil;

import java.text.DecimalFormat;

public class APUpgradeItem implements UpgradeItem {

    private double abilityPower;

    public APUpgradeItem(double abilityPower) {
        this.abilityPower = abilityPower;
    }

    @Override
    public String takeEffect(UserRole userRole) {
        userRole.changeAbilityPower(abilityPower);
        return "技能强度增加了" + FormatUtil.DECIMAL_FORMAT.format(abilityPower * 100) + "%";
    }

    @Override
    public UpgradeItemType getType() {
        return UpgradeItemType.AP;
    }
}
