package weapon;

import role.UserRole;
import util.WeaponItemUtil;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class AttackWeapon extends Weapon {

    public AttackWeapon(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getEffect() {
        List<String> effects = new Vector<>();
        effects.add("攻击力增加" + attack);
        return effects;
    }

    @Override
    public void takeEffect(UserRole userRole) {
        userRole.setWeaponAttack(this.attack);
    }

    @Override
    public void dropEffect(UserRole userRole) {
        userRole.setWeaponAttack(0);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.ATTACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttackWeapon weaponA = (AttackWeapon) o;
        return attack == weaponA.attack &&
                Objects.equals(name, weaponA.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attack);
    }
}
