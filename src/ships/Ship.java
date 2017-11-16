package ships;

import static util.Dice.d6;

public class Ship {
    private ShipClass shipClass;
    public int currentHull;
    public double currentInitiative;

    public Ship(ShipClass shipClass) {
        this.shipClass = shipClass;
        this.currentHull = shipClass.hull;
        this.currentInitiative = shipClass.initiative;
    }

    public void receiveDamage(int damage){
        this.currentHull = this.currentHull - damage < 0 ? 0 : this.currentHull - damage;
    }
    public int rollForDamage(){
        return shipClass.weapons
                .stream()
                .filter(w -> d6()>= shipClass.hitsOn)
                .map(w -> w.weaponDamage)
                .reduce(0,(a,b) -> a+b);
    }

    public boolean alive(){
        return currentHull > 0;
    }
    public void repair(){
        this.currentHull = shipClass.hull;
    }
    public String getShipClassName(){
        return shipClass.name;
    }
}
