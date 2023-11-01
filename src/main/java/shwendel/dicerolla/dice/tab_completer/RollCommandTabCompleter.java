package shwendel.dicerolla.dice.tab_completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.checkerframework.checker.units.qual.A;
import shwendel.dicerolla.dice.Dice;
import shwendel.dicerolla.dice.DiceManager;
import shwendel.dicerolla.dice.commands.RollCommand;

import java.util.*;

public class RollCommandTabCompleter implements TabCompleter {

    // Arbitrary values
    private static final String[] UNLIMITED_DICE_COMPLETIONS = new String[] { "4", "6", "8", "10", "25", "100", "1000" };
    private static final String[] MODIFIER_COMPLETIONS = new String[] { "[+1]", "[+2]", "[+3]", "[+10]", "[+20]" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length >= 0) {

            String firstArg = args[0];
            String splitFirstArg = "";
            
            boolean modifier = false;

            if(firstArg.contains(RollCommand.MODIFIER_START)) {
                
                int index = firstArg.indexOf(RollCommand.MODIFIER_START);
                
                splitFirstArg = firstArg.substring(0, index);
                firstArg = firstArg.substring(index);
                
                modifier = true;
            }

            List<String> completions;
            List<String> newCompletions = new ArrayList<>();

            if(!modifier) {

                if(DiceManager.unlimitedDice) {

                    completions = Arrays.asList(UNLIMITED_DICE_COMPLETIONS);

                } else {

                    completions = new ArrayList<>();

                    for(Dice dice : DiceManager.getAllLoadedDice()) {
                        completions.add(dice.getSides() + "");
                    }

                }

            } else {

                completions = new ArrayList<>();

                for(String completion : MODIFIER_COMPLETIONS) {
                    completions.add(splitFirstArg + completion);
                }

            }

            StringUtil.copyPartialMatches(args[0], completions, newCompletions);
            Collections.sort(newCompletions);

            return newCompletions;

        }

        return null;
    }

}
