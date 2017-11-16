package battles;

import ships.Ship;

import java.util.List;

public class SameInitiativeShips {
    private List<Ship> ships;
    private BattleSide side;

    public SameInitiativeShips(List<Ship> ships) {
        this.ships = ships;
    }
    public int allRollForDamage(){
        return ships.stream().map(ship -> ship.rollForDamage()).reduce(0,(a,b)->a+b);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public BattleSide getSide() {
        return side;
    }

    public void setSide(BattleSide side) {
        this.side = side;
    }
}
