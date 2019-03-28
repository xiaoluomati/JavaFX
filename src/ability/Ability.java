package ability;

import role.Enemy;
import role.UserRole;

public abstract class Ability {

    protected int damage = 0;

    protected int heal = 0;

    protected String name;

    public Ability(String name) {
        this.name = name;
    }

    public abstract void takeEffect(UserRole userRole, Enemy enemy);

    public String getName(){
        return this.name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHeal() {
        return heal;
    }
}
