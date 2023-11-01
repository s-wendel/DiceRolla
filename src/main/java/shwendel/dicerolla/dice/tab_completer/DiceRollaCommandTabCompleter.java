package shwendel.dicerolla.dice.tab_completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import shwendel.dicerolla.dice.Dice;
import shwendel.dicerolla.dice.DiceManager;
import shwendel.dicerolla.dice.commands.RollCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiceRollaCommandTabCompleter implements TabCompleter {

    private static final String[] COMPLETIONS = new String[] { "reload" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length >= 0) {


            List<String> completions = Arrays.asList(COMPLETIONS);
            List<String> newCompletions = new ArrayList<>();

            StringUtil.copyPartialMatches(args[0], completions, newCompletions);
            Collections.sort(newCompletions);

            return newCompletions;

        }

        return null;
    }

}
