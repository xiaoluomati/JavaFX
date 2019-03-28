package ability;

public abstract class AbilityDecorator extends Ability {

    protected Ability ability;


    public AbilityDecorator(Ability ability) {
        super(ability.name);
        this.ability = ability;
    }
}
