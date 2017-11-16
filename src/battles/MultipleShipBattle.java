package battles;

import ships.Ship;
import ships.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleShipBattle {
    private Team attackers;
    private Team defenders;
    private List<SameInitiativeShips> initiativeSteps;

    public MultipleShipBattle(List<Ship> attackers, List<Ship> defenders) {
        this.attackers = new Team(attackers);
        this.defenders = new Team(defenders);
    }

    public void fight() {
        defenders.getShips().stream().forEach(sh -> sh.currentInitiative += 0.5);
        initiativeSteps = splitShipsByInitiative();
        defineSides(initiativeSteps);
        while (attackers.alive() && defenders.alive()) {
            for (SameInitiativeShips step : initiativeSteps) {
                if (BattleSide.ATTACKER.equals(step.getSide())) {
                    defenders.receiveDamage(step.allRollForDamage());
                }
            }
        }
        //shoot all weapons of alive ships to opponent team
    }

    private void defineSides(List<SameInitiativeShips> initiativeWaves) {
        if (initiativeWaves.isEmpty())
            throw new RuntimeException("Initiation waves are empty during side defining step");

        for (SameInitiativeShips step : initiativeWaves) {
            if (!step.getShips().isEmpty())
                throw new RuntimeException("Initiation step contains no ships;");

            if (attackers.getShips().contains(step.getShips().get(0))) {
                step.setSide(BattleSide.ATTACKER);
            } else {
                step.setSide(BattleSide.DEFENDER);
            }
        }
    }

    private List<SameInitiativeShips> splitShipsByInitiative() {
        List<SameInitiativeShips> waves = new ArrayList<>();
        List<Ship> allShips = new ArrayList<>(defenders.getShips());
        allShips.addAll(attackers.getShips());

        List<Double> distinctInitiatives =
                allShips
                        .stream()
                        .map(sh -> sh.currentInitiative)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
        Collections.reverse(distinctInitiatives);

        for (Double init : distinctInitiatives) {
            waves.add(
                    new SameInitiativeShips(
                            allShips
                                    .stream()
                                    .filter(sh -> init.equals(sh.currentInitiative))
                                    .collect(Collectors.toList())));
        }
        return waves;
    }

}
