package battles.multiship;

import ships.Ship;

public class TeamedShip {
    private Ship ship;
    private BattleSide side;

    public TeamedShip(Ship ship, BattleSide side) {
        this.ship = ship;
        this.side = side;
    }

    public Ship getShip() {
        return ship;
    }

    public BattleSide getSide() {
        return side;
    }
    public String reportStatus(){
        StringBuilder sb = new StringBuilder();
        sb
                .append(ship.getShipClass())
                .append(": ")
                .append(ship.currentHull)
                .append("/")
                .append(ship.getShipClass().getHull());
        return sb.toString();
    }
}
