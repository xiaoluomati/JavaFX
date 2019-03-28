package item;


public abstract class UpgradeItemFactory {

    protected int itemLevel;

    public UpgradeItemFactory(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public abstract UpgradeItem createUpgradeItem(UpgradeItemType type);

    protected double upgradeLevel(){
        return 1 + (Math.random() / 5) + 0.1 * itemLevel;
    }

}
