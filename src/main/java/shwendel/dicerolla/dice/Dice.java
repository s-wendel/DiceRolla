package shwendel.dicerolla.dice;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    public static ThreadLocalRandom random = ThreadLocalRandom.current();

    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public int roll(int modifier) {
        return random.nextInt(1, sides + 1) + modifier;
    }

}
