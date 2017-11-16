package ships;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipClass {
    public static ShipClass INTERCEPTOR = new ShipClass("Interceptor",1,6,3,Weapons.simple());
    public static ShipClass CRUISER = new ShipClass("Cruiser",2,5,2,Weapons.simple());
    public static ShipClass DREADNOUGHT = new ShipClass("Dreadnought",3,5,1,Weapons.simple(),Weapons.simple());

    final String name;
    final int hull;
    final int hitsOn;
    final double initiative;
    List<Weapons> weapons;

    ShipClass(String name, int hull, int hitsOn, double initiative, Weapons... weapons) {
        this.name = name;
        this.hull = hull;
        this.hitsOn = hitsOn;
        this.initiative = initiative;
        this.weapons = new ArrayList<>();
        this.weapons.addAll(Arrays.asList(weapons));
    }

    public int getHull() {
        return hull;
    }

    public String getName() {
        return name;
    }
}
