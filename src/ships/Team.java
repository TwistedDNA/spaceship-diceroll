package ships;

import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private List<Ship> ships;

    public Team(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void receiveDamage(int damage){
        if(damage <= 0){
            return;
        }
    }

    public boolean alive(){
        return ships.stream().map(sh -> sh.alive()).collect(Collectors.toList()).contains(true);
    }
}
