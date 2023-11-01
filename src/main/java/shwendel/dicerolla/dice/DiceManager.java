package shwendel.dicerolla.dice;

import org.bukkit.configuration.file.FileConfiguration;
import shwendel.dicerolla.DiceRolla;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiceManager {

    private static final String DICE_NOTATION = "dice";
    private static final String MAX_SIDES_NOTATION = "max_sides";
    private static final String ERROR_MESSAGE = "Sides can only be numbers > 0. I'm freaking out";

    private static Map<Integer, Dice> dice;
    public static boolean unlimitedDice; // Can use whatever dice option in /roll
    public static int maxSides;

    static {
        reload();
    }

    public static void reload() {

        FileConfiguration config = DiceRolla.getInstance().getConfig();

        dice = new HashMap<>();
        unlimitedDice = !config.isSet(DICE_NOTATION);
        maxSides = config.getInt(MAX_SIDES_NOTATION, -1);

        if(!unlimitedDice) {

            for(String sides : config.getStringList(DICE_NOTATION)) {

                try {

                    int amount = Integer.parseInt(sides);

                    if(amount <= 0) {
                        throw new IllegalArgumentException(ERROR_MESSAGE);
                    }

                    dice.put(amount, new Dice(amount));

                } catch(NumberFormatException exception) {

                    throw new NumberFormatException(ERROR_MESSAGE);

                }

            }

        }
    }

    public static Dice getDice(int sides) {

        if(unlimitedDice) {

            if(maxSides != -1 && sides >= maxSides) {
                return null;
            }

            if(!dice.containsKey(sides)) {
                dice.put(sides, new Dice(sides));
            }

            return dice.get(sides);
        }

        return dice.getOrDefault(sides, null);
    }

    public static Collection<Dice> getAllLoadedDice() {
        return dice.values();
    }

}
