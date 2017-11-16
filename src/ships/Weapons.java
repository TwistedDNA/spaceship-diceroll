package ships;

public class Weapons {
    public int weaponDamage;

    Weapons(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
    static Weapons simple(){
        return new Weapons(1);
    }
    static Weapons advanced(){
        return new Weapons(2);
    }
    static Weapons heavy(){
        return new Weapons(4);
    }
}
