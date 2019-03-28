package weapon;

public abstract class WeaponFactory {

    protected int itemLevel;

    public WeaponFactory(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public abstract Weapon createWeapon(WeaponType weapontype);

    protected double upgradeLevel(){
        return 1 + (Math.random() / 5) + 0.1 * itemLevel;
    }

}
