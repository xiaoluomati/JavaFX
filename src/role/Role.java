package role;

public abstract class Role {

    protected int lp;

    protected int lpCeiling;

    public abstract int getDamage();

    public abstract int decreaseLp(int damage);

    public int increaseLp(int heal) {
        int u = this.lp + heal;
        this.lp = this.lpCeiling > u ? u : this.lpCeiling;
        return this.lp;
    }

}
