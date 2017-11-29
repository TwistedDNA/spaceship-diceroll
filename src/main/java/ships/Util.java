package ships;

import battles.multiship.TeamedShip;

import java.util.List;

public class Util {
    public static boolean isSomebodyAlive(List<TeamedShip> ships) {
        for (TeamedShip ship : ships) {
            if (ship.getShip().alive())
                return true;
        }
        return false;
    }

    public static void massReset(List<Ship>... shipLists) {
        for (List<Ship> list : shipLists)
            for (Ship ship : list) {
                ship.reset();
            }
    }
    public static String announceTeam(List<Ship> ships, String teamName){
        StringBuilder sb = new StringBuilder();
        sb.append(teamName).append(":").append(System.lineSeparator());
        for(Ship ship : ships){
            sb.append(ship.getShipClass().getName()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
