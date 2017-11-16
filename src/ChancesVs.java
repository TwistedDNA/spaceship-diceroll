import java.util.Random;

public class ChancesVs {

    public static void main(String[] args) {
        Ship interceptor = new Ship(1, 6, 1);
        Ship cruiser = new Ship(2, 5, 1);
        Ship dreadnought = new Ship(3, 5, 2);
        System.out.println("Interceptor vs Interceptor");
        System.out.println(expectedPowerComparisson(interceptor, interceptor));
        System.out.println(simulateBattle(interceptor, interceptor, 100000));

        System.out.println("Interceptor vs Cruiser");
        System.out.println(expectedPowerComparisson(interceptor, cruiser));
        System.out.println(simulateBattle(interceptor, cruiser, 100000));

        System.out.println("Interceptor vs Dreadnought");
        System.out.println(expectedPowerComparisson(interceptor, dreadnought));
        System.out.println(simulateBattle(interceptor, dreadnought, 100000));
    }

    private static String expectedPowerComparisson(Ship s1, Ship s2) {
        double expectedDamagePerTurnFromFirst = ((7.0 - s1.hitsOn) / 6) * s1.damage;
        double expectedDamagePerTurnFromSecond = ((7.0 - s2.hitsOn) / 6) * s2.damage;
        double expectedLifetimeFirst = ((double) s1.wounds) / expectedDamagePerTurnFromSecond;
        double expectedLifetimeSecond = ((double) s2.wounds) / expectedDamagePerTurnFromFirst;
        double chanceToWinFirst = expectedLifetimeFirst / (expectedLifetimeFirst + expectedLifetimeSecond) * 100;
        return String.format("%3.1f vs %3.1f", chanceToWinFirst, 100 - chanceToWinFirst);
    }

    private static String simulateBattle(Ship s1, Ship s2, int times) {
        int s1Win = 0;
        int s2Win = 0;
        for (int i = 0; i < times; i++) {
            int res = battle(s1, s2);
            if (res > 0) {
                s1Win++;
            }
            if (res < 0) {
                s2Win++;
            }
        }
        return String.format("%3.1f vs %3.1f", ((double) s1Win) / (s1Win + s2Win) * 100, ((double) s2Win) / (s1Win + s2Win) * 100);
    }

    private static int battle(Ship s1, Ship s2) {
        int s1Health = s1.wounds;
        int s2Health = s2.wounds;
        //System.out.println("Start: "+s1Health+" "+s2Health);
        while (true) {
            s1Health = d6() >= s2.hitsOn ? s1Health - s2.damage : s1Health;
            s2Health = d6() >= s1.hitsOn ? s2Health - s1.damage : s2Health;
            //System.out.println(s1Health+" "+s2Health);
            if (s1Health <= 0 && s2Health <= 0) {
                return 0;
            }
            if (s1Health <= 0) {
                return -1;
            }
            if (s2Health <= 0) {
                return 1;
            }
        }
    }

    private static int d6() {
        return new Random().nextInt(6) + 1;
    }

    static class Ship {
        int wounds; //1..many
        int hitsOn; //1..6
        int damage;//1..many

        public Ship(int wounds, int hitsOn, int damage) {
            this.wounds = wounds;
            this.hitsOn = hitsOn;
            this.damage = damage;
        }
    }
}
