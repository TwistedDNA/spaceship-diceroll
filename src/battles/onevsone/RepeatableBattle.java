package battles.onevsone;

import ships.Ship;

public class RepeatableBattle {
    private Ship attacker;
    private Ship defender;

    public RepeatableBattle(Ship attacker, Ship defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void fight() {
        final int times = 50000;
        int nOfAttackerWins = 0;
        int nOfDefenderWins = 0;
        int res;

        for (int i = 0; i < times; i++) {
            attacker.reset();
            defender.reset();

            Battle b = new Battle(attacker, defender);
            res = b.fight();

            nOfAttackerWins += res == 1 ? 1 : 0;
            nOfDefenderWins += res == 2 ? 1 : 0;
        }

        double attackerWinPercent = ((double) nOfAttackerWins) / times * 100;
        double defenderWinPercent = ((double) nOfDefenderWins) / times * 100;

        System.out.println(String.format(attacker.getShipClass().getName() + " attacks " + defender.getShipClass().getName() + ": (W:%4.1f%% L:%4.1f%%)", attackerWinPercent, defenderWinPercent));
    }

}
