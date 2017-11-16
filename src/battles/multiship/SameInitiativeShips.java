package battles.multiship;


import java.util.ArrayList;
import java.util.List;

public class SameInitiativeShips {
    private List<TeamedShip> ships;
    private BattleSide side;

    public SameInitiativeShips(List<TeamedShip> ships) {
        this.ships = ships;
    }
    public List<Integer> allRollForDamage(){
        List<Integer> damageInstances = new ArrayList<>();
        ships.stream().map(ship -> ship.getShip().rollForDamage()).forEach(d -> damageInstances.addAll(d));
        return damageInstances;
    }

    public List<TeamedShip> getShips() {
        return ships;
    }

    public BattleSide getSide() {
        return side;
    }

    public void setSide(BattleSide side) {
        this.side = side;
    }
}
