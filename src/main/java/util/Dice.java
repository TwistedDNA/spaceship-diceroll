package util;

import java.util.Random;

public class Dice {
    public static int d6(){
        return new Random().nextInt(6)+1;
    }
}
