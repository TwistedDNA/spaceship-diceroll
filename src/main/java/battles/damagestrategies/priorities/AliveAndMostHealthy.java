package battles.damagestrategies.priorities;

import battles.multiship.TeamedShip;

import java.util.Comparator;

public class AliveAndMostHealthy implements Comparator<TeamedShip> {
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