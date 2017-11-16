package battles.multiship;

import ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class MultishipBattle {
    private List<TeamedShip> attackers = new ArrayList<>();
    private List<TeamedShip> defenders = new ArrayList<>();

    public MultishipBattle(List<Ship> attackers, List<Ship> defenders) {
        attackers.forEach(ship -> this.attackers.add(new TeamedShip(ship, BattleSide.ATTACKER)));
        defenders.forEach(ship -> this.defenders.add(new TeamedShip(ship, BattleSide.DEFENDER)));
    }

    public void fight() {
        //split by initiative
        //while both teams alive
        //initiative step - do damage
    }

    private void showResults() {
        System.out.println();
    }
}
