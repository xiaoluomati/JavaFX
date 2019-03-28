package role;

public abstract class EnemyBuilder {

    protected int level;

    protected Enemy enemy;

    public EnemyBuilder(int level) {
        this.level = level;
    }

    protected abstract void addExp();

    protected void addItems(){}

    protected void addWeapons(){}

    protected abstract void addBehavior();

    public final Enemy createEnemy(){
        this.enemy = new Enemy();
        this.addBehavior();
        this.addExp();
        this.addItems();
        this.addWeapons();
        return this.enemy;
    }

    protected double upgradeLevel(){
        return 1 + (Math.random() / 5) + 0.1 * level;
    }

}
