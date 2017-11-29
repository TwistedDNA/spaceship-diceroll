package battles.damagestrategies;

import battles.damagestrategies.priorities.AliveAndMostHealthy;
import battles.damagestrategies.priorities.LargestAndMostDamagedFirst;
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
                return new DestroyMostAndDamageByPriority(damageInstances, beingDamaged, new LargestAndMostDamagedFirst());
            case DESTROY_MOST_AND_DAMAGE_SMALLEST:
                return new DestroyMostAndDamageByPriority(damageInstances, beingDamaged, new AliveAndMostHealthy());
            default:
                throw new IllegalArgumentException("No damage dealing strategy found for type " + type);
        }
    }
}
