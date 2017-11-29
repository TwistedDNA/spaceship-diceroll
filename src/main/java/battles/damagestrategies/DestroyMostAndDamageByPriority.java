package battles.damagestrategies;

import battles.multiship.TeamedShip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DestroyMostAndDamageByPriority implements DamageDealingStrategy {
    private List<Integer> damageInstances;
    private List<TeamedShip> beingDamaged;
    Comparator<TeamedShip> priority;

    public DestroyMostAndDamageByPriority(List<Integer> damageInstances, List<TeamedShip> beingDamaged, Comparator<TeamedShip> priority) {
        this.damageInstances = damageInstances;
        this.beingDamaged = beingDamaged;
        this.priority = priority;
    }

    @Override
    public void applyDamage() {
        fitDamageDestroyingShipsInOneShot();
        damageAccordingToPriority();
    }

    private void damageAccordingToPriority() {
        List<TeamedShip> sortedByHullLeft = new ArrayList<>(beingDamaged);
        sortedByHullLeft.sort(priority);
        Iterator<Integer> it = damageInstances.iterator();
        while (it.hasNext()) {
            Integer damageInstance = it.next();
            TeamedShip shipToDamage = smallestHullLeftAlive(sortedByHullLeft);
            if (shipToDamage == null) {
                break;
            }
            shipToDamage.getShip().receiveDamage(damageInstance);
        }
    }

    private TeamedShip smallestHullLeftAlive(List<TeamedShip> sortedByHullLeft) {
        for (TeamedShip ship : sortedByHullLeft) {
            if (ship.getShip().alive()) {
                return ship;
            }
        }
        return null;
    }

    private void fitDamageDestroyingShipsInOneShot() {
        Iterator<Integer> it = damageInstances.iterator();
        while (it.hasNext()) {
            Integer damageInstance = it.next();
            for (TeamedShip potentialAim : beingDamaged) {
                if (potentialAim.getShip().alive() && damageInstance.equals(potentialAim.getShip().currentHull)) {
                    potentialAim.getShip().receiveDamage(damageInstance);
                    it.remove();
                    break;
                }
            }
        }
    }


}
