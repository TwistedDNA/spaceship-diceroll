package battles.onevsone;

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
                defender.receiveDamage(damageFrom(attacker));
                if (!defender.alive()) {
                    return 1;
                }
                attacker.receiveDamage(damageFrom(defender));
                if (!attacker.alive()) {
                    return 2;
                }
            }else{
                attacker.receiveDamage(damageFrom(defender));
                if (!attacker.alive()) {
                    return 2;
                }
                defender.receiveDamage(damageFrom(attacker));
                if (!defender.alive()) {
                    return 1;
                }
            }
        }
        return 0;
    }
    private int damageFrom(Ship s){
        return s.rollForDamage().stream().mapToInt(Integer::intValue).sum();
    }

}
