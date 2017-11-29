package battles.damagestrategies.priorities;

import battles.multiship.TeamedShip;

import java.util.Comparator;

public class LargestAndMostDamagedFirst implements Comparator<TeamedShip> {
    @Override
    public int compare(TeamedShip o1, TeamedShip o2) {
        int dif = (o1.getShip().getShipClass().getHull() - o2.getShip().getShipClass().getHull()) * 5;
        dif += o1.getShip().currentHull - o2.getShip().currentHull;
        return dif;
    }
}
