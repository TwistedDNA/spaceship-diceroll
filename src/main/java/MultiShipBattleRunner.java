
import battles.multiship.BattleSide;
import battles.multiship.MultiShipBattle;
import ships.Ship;
import ships.Shipyard;
import ships.Util;

import java.util.Arrays;
import java.util.List;

public class MultiShipBattleRunner {
    public static void main(String[] args) {
        collectStatistics();
    }

    private static void collectStatistics(){
        battleHarness(
                Arrays.asList(
                        Shipyard.buildInterceptor(),
                        Shipyard.buildDreadnought()
                ),
                Arrays.asList(
                        Shipyard.buildDreadnought()
                )
        );
    }

    private static void battleHarness(List<Ship> attackers, List<Ship> defenders) {
        int winsOfAttackers = 0;
        System.out.println(Util.announceTeam(attackers, "Attackers"));
        System.out.println(Util.announceTeam(defenders, "Defenders"));
        for(int i=0;i<100000;i++){
            winsOfAttackers += BattleSide.ATTACKER.equals(new MultiShipBattle(attackers,defenders).fight().winner()) ? 1 : 0;
            Util.massReset(attackers,defenders);
        }
        double percentage = ((double)winsOfAttackers) / 1000;
        System.out.println(String.format("Attackers win percentage: %4.1f", percentage));
    }
}
