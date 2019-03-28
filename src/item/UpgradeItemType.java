package item;

public enum UpgradeItemType {
    WATTACK,LPC,LP,RATTACK,AP;

    public static UpgradeItemType getByInt(int type){
        for (UpgradeItemType upgradeItemType : UpgradeItemType.values()) {
            if(upgradeItemType.ordinal() == type)
                return upgradeItemType;
        }
        return LP;
    }
}
