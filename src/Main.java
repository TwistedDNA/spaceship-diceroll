import battles.RepeatableBattle;
import ships.Shipyard;

public class Main {
    public static void main(String[] args) {
        new RepeatableBattle(
                Shipyard.buildInterceptor(),
                Shipyard.buildCruiser())
                .fight();
        new RepeatableBattle(
                Shipyard.buildCruiser(),
                Shipyard.buildCruiser())
                .fight();
        new RepeatableBattle(
                Shipyard.buildCruiser(),
                Shipyard.buildDreadnought())
                .fight();
        new RepeatableBattle(
                Shipyard.buildInterceptor(),
                Shipyard.buildDreadnought())
                .fight();
    }
}
