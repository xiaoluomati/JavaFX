package weapon;

public enum WeaponType {
    ATTACK,ABILITY;

    public static WeaponType getByInt(int type){
        for (WeaponType weaponType : WeaponType.values()) {
            if(weaponType.ordinal() == type)
                return weaponType;
        }
        return ATTACK;
    }
}
