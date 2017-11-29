package battles.multiship;

import battles.damagestrategies.DamageDealingStrategy;
import battles.damagestrategies.DamageDealingStrategyBuilder;
import ships.Ship;
import ships.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultiShipBattle {
    private List<TeamedShip> attackers = new ArrayList<>();
    private List<TeamedShip> defenders = new ArrayList<>();
    private DamageDealingStrategyBuilder damageDistributor;

    public MultiShipBattle(List<Ship> attackers, List<Ship> defenders, DamageDealingStrategyBuilder damageDistributor) {
        attackers.forEach(ship -> this.attackers.add(new TeamedShip(ship, BattleSide.ATTACKER)));
        defenders.forEach(ship -> this.defenders.add(new TeamedShip(ship, BattleSide.DEFENDER)));
        this.damageDistributor = damageDistributor;
    }

    public MultiShipBattle fight() {
        benefitDefendersInitiative();
        List<SameInitiativeShips> initiativeSteps = separateInitiativeSteps();
        int i = 0;
        while (!isBattleEnded()) {
            battleStep(initiativeSteps.get(i % initiativeSteps.size()));
            i++;
        }
        return this;
    }

    private void battleStep(SameInitiativeShips firingSide) {
        List<Integer> damage = firingSide.allRollForDamage();
        if (firingSide.getSide().equals(BattleSide.ATTACKER)) {
            receiveDamage(damage, defenders);
        } else {
            receiveDamage(damage, attackers);
        }
    }

    private void receiveDamage(List<Integer> damageInstances, List<TeamedShip> beingDamaged) {
        DamageDealingStrategy strategy = damageDistributor.build(damageInstances, beingDamaged);
        strategy.applyDamage();
    }

    private boolean isBattleEnded() {
        return !(Util.isSomebodyAlive(attackers) && Util.isSomebodyAlive(defenders));
    }

    private void benefitDefendersInitiative() {
        defenders.stream().forEach(sh -> sh.getShip().currentInitiative += 0.5);
    }

    private List<SameInitiativeShips> separateInitiativeSteps() {
        List<TeamedShip> allShips = new ArrayList<>(attackers);
        allShips.addAll(defenders);
        Collections.sort(allShips, Comparator.comparingDouble(o -> o.getShip().currentInitiative));
        Collections.reverse(allShips);
        List<SameInitiativeShips> steps = new ArrayList<>();
        allShips.stream().map(sh -> sh.getShip().currentInitiative).distinct()
                .forEach(init ->
                        steps.add(new SameInitiativeShips(
                                allShips
                                        .stream()
                                        .filter(s -> init.equals(s.getShip().currentInitiative))
                                        .collect(Collectors.toList())
                        ))
                );
        for (SameInitiativeShips step : steps) {
            if (!step.verify()) {
                throw new RuntimeException("Initiative step is invalid due to different sides together");
            }
        }
        return steps;
    }

    public void printResults() {
        StringBuilder shipStates = new StringBuilder();
        shipStates.append("Attackers: ").append(System.lineSeparator());
        for (TeamedShip ship : attackers) {
            shipStates.append(ship.getShip().reportStatus()).append(System.lineSeparator());
        }
        shipStates.append("Defenders: ").append(System.lineSeparator());
        for (TeamedShip ship : defenders) {
            shipStates.append(ship.getShip().reportStatus()).append(System.lineSeparator());
        }
        System.out.println(shipStates.toString());
    }

    public BattleSide winner() {
        if (Util.isSomebodyAlive(attackers) && !Util.isSomebodyAlive(defenders))
            return BattleSide.ATTACKER;
        if (!Util.isSomebodyAlive(attackers) && Util.isSomebodyAlive(defenders))
            return BattleSide.DEFENDER;
        return null;
    }
}
