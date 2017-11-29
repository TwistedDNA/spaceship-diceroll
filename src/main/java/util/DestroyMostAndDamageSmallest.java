package util;

import battles.multiship.TeamedShip;

import java.util.*;

public class DestroyMostAndDamageSmallest {
    List<Integer> damageInstances;
    List<TeamedShip> beingDamaged;

    public DestroyMostAndDamageSmallest(List<Integer> damageInstances, List<TeamedShip> beingDamaged) {
        this.damageInstances = damageInstances;
        this.beingDamaged = beingDamaged;
    }

    public void applyDamage() {
        fitDamageDestroyingShipsInOneShot();
        damageFirstUntilDead();
    }

    private void damageFirstUntilDead() {
        List<TeamedShip> sortedByHullLeft = new ArrayList<>(beingDamaged);
        sortedByHullLeft.sort(new HullLeftComparator());
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

    class HullLeftComparator implements Comparator<TeamedShip> {
        @Override
        public int compare(TeamedShip o1, TeamedShip o2) {
            int baseDiff = o1.getShip().currentHull - o2.getShip().currentHull;
            if (!o1.getShip().alive() && o2.getShip().alive()) {
                return baseDiff - 10;
            } else if (o1.getShip().alive() && !o2.getShip().alive()) {
                return baseDiff + 10;
            }
            return baseDiff;
        }
    }
}
