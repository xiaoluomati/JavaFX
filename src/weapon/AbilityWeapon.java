package weapon;

import role.UserRole;
import util.FormatUtil;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class AbilityWeapon extends Weapon {

    private double abilityPower;

    public AbilityWeapon(String name) {
        super(name);
    }

    public AbilityWeapon(String name, double abilityPower, int attack) {
        super(name);
        this.abilityPower = abilityPower;
        this.attack = attack;
    }

    @Override
    public List<String> getEffect() {
        List<String> effects = new Vector<>();
        effects.add("技能强度增加" + FormatUtil.DECIMAL_FORMAT.format(abilityPower * 100) + "%");
        effects.add("攻击力增加" + this.attack);
        return effects;
    }

    @Override
    public void takeEffect(UserRole userRole) {
        userRole.setWeaponAttack(this.attack);
        userRole.changeAbilityPower(abilityPower);
    }

    @Override
    public void dropEffect(UserRole userRole) {
        userRole.setWeaponAttack(0);
        userRole.changeAbilityPower(-abilityPower);
    }

    @Override
    public WeaponType getType() {
        return WeaponType.ABILITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbilityWeapon that = (AbilityWeapon) o;
        return Double.compare(that.abilityPower, abilityPower) == 0 && attack == that.attack
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attack, abilityPower);
    }
}
