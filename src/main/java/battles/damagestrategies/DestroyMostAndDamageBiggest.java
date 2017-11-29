package battles.damagestrategies;

import battles.multiship.TeamedShip;

import java.util.List;

public class DestroyMostAndDamageBiggest implements DamageDealingStrategy {
    List<Integer> damageInstances;
    List<TeamedShip> beingDamaged;

    public DestroyMostAndDamageBiggest(List<Integer> damageInstances, List<TeamedShip> beingDamaged) {
        this.damageInstances = damageInstances;
        this.beingDamaged = beingDamaged;
    }

    @Override
    public void applyDamage() {
        throw new UnsupportedOperationException();
    }
}
