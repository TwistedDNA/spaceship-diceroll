package battles.multiship;

import ships.Ship;
import util.DamageDealerStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultishipBattle {
    private List<TeamedShip> attackers = new ArrayList<>();
    private List<TeamedShip> defenders = new ArrayList<>();

    public MultishipBattle(List<Ship> attackers, List<Ship> defenders) {
        attackers.forEach(ship -> this.attackers.add(new TeamedShip(ship, BattleSide.ATTACKER)));
        defenders.forEach(ship -> this.defenders.add(new TeamedShip(ship, BattleSide.DEFENDER)));
    }

    public void fight() {
        benefitDefendersInitiative();
        //split by initiative
        List<SameInitiativeShips> initiativeSteps = separateInitiativeSteps();
        int i=0;
        while (!isBattleEnded()){
            battleStep(initiativeSteps.get(i % initiativeSteps.size()));
            i++;
        }
        showResults();
    }
    private void battleStep(SameInitiativeShips firingSide){
        List<Integer> damage = firingSide.allRollForDamage();
        if(firingSide.getSide().equals(BattleSide.ATTACKER)){
            receiveDamage(damage,defenders);
        }else{
            receiveDamage(damage,attackers);
        }
    }
    private void receiveDamage(List<Integer> damageInstances, List<TeamedShip> beingDamaged){
        DamageDealerStrategy strategy = new DamageDealerStrategy(damageInstances,beingDamaged);
        strategy.applyDamage();
    }
    private boolean isBattleEnded(){
        return isSomebodyAlive(attackers) && isSomebodyAlive(defenders);
    }
    private boolean isSomebodyAlive(List<TeamedShip> ships){
        for(TeamedShip ship: ships){
            if(ship.getShip().alive())
                return true;
        }
        return false;
    }
    private void benefitDefendersInitiative() {
        defenders.stream().forEach(sh -> sh.getShip().currentInitiative+=0.5);
    }

    private List<SameInitiativeShips> separateInitiativeSteps() {
        List<TeamedShip> allShips = new ArrayList<>(attackers);
        allShips.addAll(defenders);
        Collections.sort(allShips,Comparator.comparingDouble(o -> o.getShip().currentInitiative));
        Collections.reverse(allShips);
        List<SameInitiativeShips> steps = new ArrayList<>();
        allShips.stream().map(sh -> sh.getShip().currentInitiative).distinct()
                .forEach(init ->
                        steps.add(new SameInitiativeShips(
                              allShips
                                      .stream()
                                      .filter(s->init.equals(s.getShip().currentInitiative))
                                .collect(Collectors.toList())
                        ))
                );
        for(SameInitiativeShips step : steps){
            if(!step.verify()){
                throw new RuntimeException("Initiative step is invalid due to different sides together");
            }
        }
        return steps;
    }

    private void showResults() {
        StringBuilder shipStates = new StringBuilder();
        shipStates.append("Attackers: ").append(System.lineSeparator());
        for(TeamedShip ship : attackers){
            shipStates.append(ship.reportStatus()).append(System.lineSeparator());
        }
        shipStates.append("Defenders: ").append(System.lineSeparator());
        for(TeamedShip ship : defenders){
            shipStates.append(ship.reportStatus()).append(System.lineSeparator());
        }
    }
}
