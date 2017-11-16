package battles;

import ships.Ship;

public class Battle {
    private Ship attacker;
    private Ship defender;

    public Battle(Ship s1, Ship s2) {
        this.attacker = s1;
        this.defender = s2;
    }

    public int fight() {
        while (attacker.alive() && defender.alive()) {
            if (attacker.currentInitiative > defender.currentInitiative ) {
                defender.receiveDamage(attacker.rollForDamage());
                if (!defender.alive()) {
                    return 1;
                }
                attacker.receiveDamage(defender.rollForDamage());
                if (!attacker.alive()) {
                    return 2;
                }
            }else{
                attacker.receiveDamage(defender.rollForDamage());
                if (!attacker.alive()) {
                    return 2;
                }
                defender.receiveDamage(attacker.rollForDamage());
                if (!defender.alive()) {
                    return 1;
                }
            }
        }
        return 0;
    }

}
