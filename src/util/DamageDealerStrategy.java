package util;

import battles.multiship.TeamedShip;

import java.util.Iterator;
import java.util.List;

public class DamageDealerStrategy {
    List<Integer> damageInstances;
    List<TeamedShip> beingDamaged;
    public DamageDealerStrategy(List<Integer> damageInstances, List<TeamedShip> beingDamaged) {
        this.damageInstances = damageInstances;
        this.beingDamaged = beingDamaged;
    }

    public void applyDamage() {
        fitDamageDestroyingShipsInOneShot();
        damageFirstUntilDead();
    }

    private void damageFirstUntilDead() {
        //TODO damage ship until dead, then next.
        //TODO check ifAllShipsAreDestroyed
    }

    private void fitDamageDestroyingShipsInOneShot() {
        Iterator<Integer> it = damageInstances.iterator();
        while(it.hasNext()){
            Integer damageInstance = it.next();
            for(TeamedShip potentialAim : beingDamaged){
                if(potentialAim.getShip().alive() && damageInstance.equals(potentialAim.getShip().currentHull)){
                    potentialAim.getShip().receiveDamage(damageInstance);
                    it.remove();
                    break;
                }
            }
        }
    }

    private void destroyEverybody() {
        beingDamaged.forEach(sh-> sh.getShip().receiveDamage(sh.getShip().currentHull));
    }

    private boolean canDestroyEverybody() {
        int damageTotal = damageInstances.stream().reduce(0,(a,b) -> a+b);
        //TODO can I cover all ships with damage instances?

        //TODO check
        return false;
    }
}
