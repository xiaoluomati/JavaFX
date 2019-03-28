package role;

import ability.Ability;
import ability.AbilityState;
import ability.NormalState;
import util.RoleUtil;
import item.UpgradeItem;
import weapon.Weapon;

import java.util.List;
import java.util.Vector;

public class Enemy extends Role{

    private int attack = RoleUtil.ENEMY_DEFAULT_ATTACK;

    private int exp = RoleUtil.EXP_PER_LEVEL / 2;

    private Behavior behavior;

    private List<Weapon> weapons = new Vector<>();

    private List<UpgradeItem> items = new Vector<>();

    private AbilityState state = NormalState.getInstance();

    private int armor = 0;

    public AbilityState getState() {
        return state;
    }

    public void setState(AbilityState state) {
        this.state.dropEffect(this);
        this.state = state;
        this.state.takeEffect(this);
    }

    public Enemy() {
        this.lp = RoleUtil.ENEMY_DEFAULT_LP;
        this.lpCeiling = RoleUtil.ENEMY_DEFAULT_LP;
    }

    public List<UpgradeItem> getItems() {
        return items;
    }

    public void setItems(List<UpgradeItem> items) {
        this.items = items;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public int getExp() {
        return exp;
    }

    public int getLp() {
        return lp;
    }

    public int getLpCeiling() {
        return lpCeiling;
    }

    public int getAttack() {
        return attack;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDamage() {
        return this.behavior.damageEnemy(this.attack);
    }

    @Override
    public int decreaseLp(int damage) {
        damage = this.behavior.getEnemyDamage(damage);
        damage = (int) ((1 - armor / 100.0) * damage);
        if(damage > this.lp){
            this.lp = 0;
        }else {
            this.lp -= damage;
        }
        return this.lp;
    }

}
