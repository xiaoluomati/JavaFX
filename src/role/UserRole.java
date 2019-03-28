package role;

import ability.Ability;
import ability.AbilityDecoratorA;
import util.RoleUtil;
import weapon.Weapon;

import java.util.List;
import java.util.Vector;

public class UserRole extends Role{

    private double abilityPower;

    private int exp;

    private int totalExp;

    private int level;

    private int attack ;

    private int weaponAttack;

    private List<Weapon> weapons;

    private List<Ability> abilities;

    private Weapon weapon = null;

    private Behavior behavior;

    private UserRole(Behavior behavior) {
        this.behavior = behavior;
        init();
    }

    public void init(){
        this.lp = RoleUtil.ROLE_DEFAULT_LP;
        this.lpCeiling = RoleUtil.ROLE_DEFAULT_LP;
        this.attack = RoleUtil.ROLE_DEFAULT_ATTACK;
        this.weapons = new Vector<>();
        this.abilities = new Vector<>();
        this.abilityPower = 1.0;
        this.exp = 0;
        this.totalExp = 0;
        this.level = 1;
        this.behavior.setRoleBaseValue(this);
    }

    private volatile static UserRole role;

    public static synchronized UserRole getRole(Behavior behavior){
        if(role == null){
            synchronized (UserRole.class){
                if(role == null){
                    role = new UserRole(behavior);
                }else {
                    role.setBehavior(behavior);
                }
            }
        }
        return role.setBehavior(behavior);
    }

    public double getAbilityPower() {
        return abilityPower;
    }

    public double changeAbilityPower(double change){
        this.abilityPower += change;
        return this.abilityPower;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void upgradeWeapon(int attack){
        this.weapon.dropEffect(this);
        this.weapon.upAttack(attack);
        this.weapon.takeEffect(this);
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapons.contains(weapon)){
            if (this.weapon != null) {
                this.weapon.dropEffect(this);
            }
            this.weapon = weapon;
            this.weapon.takeEffect(this);
        }
    }

    public boolean addAbility(Ability ability){
        if(ability == null){
            return false;
        }
        this.abilities.add(ability);
        return true;
    }

    public int getBaseAttack() {
        return this.attack;
    }

    public int getAttack(){
        return this.attack + this.weaponAttack;
    }

    public void decBaseAttack(int attack){
        this.attack -= attack;
    }

    public boolean addWeapon(Weapon weapon){
        if(weapon == null){
            return false;
        }
        this.weapons.add(weapon);
        return true;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public int getLevel(){
        return level;
    }

    public int getExp(){
        return exp;
    }

    public void addExp(int exp){
        if(exp > 0){
            this.totalExp += exp;
            this.exp = this.totalExp % RoleUtil.EXP_PER_LEVEL;
            int newLevel = this.totalExp / RoleUtil.EXP_PER_LEVEL + 1;
            for (int i = 0; i < newLevel - this.level; i++) {
                this.lpCeiling += (int) (RoleUtil.ROLE_DEFAULT_LP * 0.2);
                this.lp = this.lpCeiling;
                this.attack += (int) (RoleUtil.ROLE_DEFAULT_ATTACK * 0.2);
                for (int i1 = 0; i1 < this.getAbilities().size(); i1++) {
                    this.abilities.set(i1, new AbilityDecoratorA(this.abilities.get(i1)));
                }
            }
            this.level = newLevel;
        }
    }

    public int getLp() {
        return lp;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public int getLpCeiling() {
        return lpCeiling;
    }

    public UserRole setBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public void dropWeapon(Weapon weapon){
        this.weapons.remove(weapon);
        if(this.weapon != null && this.weapon.equals(weapon)){
            this.weapon.dropEffect(this);
            if (!this.weapons.isEmpty()) {
                this.weapon = this.weapons.get(0);
            }
        }
    }

    public void addLpc(int lpc){
        this.lpCeiling += lpc;
        this.lp += lpc;
    }

    public void addBaseAttack(int attack){
        this.attack += attack;
    }

    public int getWeaponAttack() {
        return weaponAttack;
    }

    public void setWeaponAttack(int weaponAttack) {
        this.weaponAttack = weaponAttack;
    }

    @Override
    public int getDamage(){
        return this.behavior.damageEnemy(this.getAttack());
    }

    @Override
    public int decreaseLp(int damage){
        int actual = this.behavior.getEnemyDamage(damage);
        if (this.lp >= actual){
            this.lp -= actual;
        }else{
            this.lp = 0;
        }
        return this.lp;
    }
}
