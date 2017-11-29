package battles.damagestrategies;

import battles.multiship.TeamedShip;

import java.util.List;

public class DamageDealingStrategyBuilder {
    private DamageDealingStrategyType type;

    public DamageDealingStrategyBuilder(DamageDealingStrategyType type) {
        this.type = type;
    }

    public DamageDealingStrategy build(List<Integer> damageInstances, List<TeamedShip> beingDamaged) {
        switch (type) {
            case DESTROY_MOST_AND_DAMAGE_BIGGEST:
                return new DestroyMostAndDamageBiggest(damageInstances, beingDamaged);
            case DESTROY_MOST_AND_DAMAGE_SMALLEST:
                return new DestroyMostAndDamageSmallest(damageInstances, beingDamaged);
            default:
                throw new IllegalArgumentException("No damage dealing strategy found for type " + type);
        }
    }
}
