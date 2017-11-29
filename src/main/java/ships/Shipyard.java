package ships;

public class Shipyard {
    public static Ship buildInterceptor() {
        return new Ship(ShipClass.INTERCEPTOR);
    }

    public static Ship buildCruiser() {
        return new Ship(ShipClass.CRUISER);
    }

    public static Ship buildDreadnought() {
        return new Ship(ShipClass.DREADNOUGHT);
    }
}
