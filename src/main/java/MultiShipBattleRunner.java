
import battles.damagestrategies.DamageDealingStrategyBuilder;
import battles.damagestrategies.DamageDealingStrategyType;
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

    private static void collectStatistics() {
        prepareBattle(
                Arrays.asList(
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildDreadnought(),
                        Shipyard.buildDreadnought()
                ),
                Arrays.asList(
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildDreadnought(),
                        Shipyard.buildDreadnought()
                ),
                new DamageDealingStrategyBuilder(DamageDealingStrategyType.DESTROY_MOST_AND_DAMAGE_BIGGEST),
                new DamageDealingStrategyBuilder(DamageDealingStrategyType.DESTROY_MOST_AND_DAMAGE_SMALLEST)
        );
        prepareBattle(
                Arrays.asList(
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildDreadnought(),
                        Shipyard.buildDreadnought()
                ),
                Arrays.asList(
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildInterceptor(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildCruiser(),
                        Shipyard.buildDreadnought(),
                        Shipyard.buildDreadnought()
                ),
                new DamageDealingStrategyBuilder(DamageDealingStrategyType.DESTROY_MOST_AND_DAMAGE_SMALLEST),
                new DamageDealingStrategyBuilder(DamageDealingStrategyType.DESTROY_MOST_AND_DAMAGE_BIGGEST)
        );
    }

    private static void prepareBattle(List<Ship> attackers, List<Ship> defenders, DamageDealingStrategyBuilder attackersStrategy, DamageDealingStrategyBuilder defendersStrategy) {
        int winsOfAttackers = 0;
        System.out.println(Util.announceTeam(attackers, "Attackers"));
        System.out.println(Util.announceTeam(defenders, "Defenders"));
        for (int i = 0; i < 100000; i++) {
            winsOfAttackers += BattleSide.ATTACKER.equals(new MultiShipBattle(attackers, defenders, attackersStrategy, defendersStrategy).fight().winner()) ? 1 : 0;
            Util.massReset(attackers, defenders);
        }
        double percentage = ((double) winsOfAttackers) / 1000;
        System.out.println(String.format("Attackers win percentage: %4.1f", percentage));
    }
}
