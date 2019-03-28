package weapon;


import role.UserRole;
import util.WeaponItemUtil;

import java.util.List;

public abstract class Weapon {

    protected String name;

    protected int attack = WeaponItemUtil.WEAPON_DEFAULT_ATTACK;

    public Weapon(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName(){
        return this.name;
    }

    public abstract List<String> getEffect();

    public int upAttack(int attack) {
        this.attack += attack;
        return this.attack;
    }

    public abstract void takeEffect(UserRole userRole);

    public abstract void dropEffect(UserRole userRole);

    public abstract WeaponType getType();

}
