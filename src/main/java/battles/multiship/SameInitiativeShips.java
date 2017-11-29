package battles.multiship;


import java.util.ArrayList;
import java.util.List;

public class SameInitiativeShips {
    private List<TeamedShip> ships;
    private BattleSide side;

    public SameInitiativeShips(List<TeamedShip> ships) {
        if (ships.size() == 0) {
            throw new RuntimeException("Initiative step could not be without ships.");
        }
        side = ships.get(0).getSide();
        this.ships = ships;
    }

    public List<Integer> allRollForDamage() {
        List<Integer> damageInstances = new ArrayList<>();
        ships.stream().filter(s -> s.getShip().alive()).map(ship -> ship.getShip().rollForDamage()).forEach(d -> damageInstances.addAll(d));
        return damageInstances;
    }

    public boolean verify() {
        for(TeamedShip ts : ships){
            if (!side.equals(ts.getSide())){
                return false;
            }
        }
        return true;
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
