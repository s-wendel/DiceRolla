package shwendel.dicerolla.dice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.units.qual.A;
import shwendel.dicerolla.DiceRolla;
import shwendel.dicerolla.dice.Dice;
import shwendel.dicerolla.dice.DiceManager;
import shwendel.dicerolla.dice.events.DiceRollEvent;
import shwendel.dicerolla.messages.DiceRollaMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RollCommand {

    public static final String MODIFIER_START = "[";
    public static final String MODIFIER_END = "]";

    private static final String PERMISSION = "dicerolla.broadcast";

    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args, boolean broadcast) {

        if(broadcast) {

            if(!sender.hasPermission(PERMISSION)) {

                for(String message : DiceRollaMessage.NO_PERMISSION.getMessages()) {
                    sender.sendMessage(message);
                }
                return true;

            }

        }

        if(args.length == 0) {

            for(String message : DiceRollaMessage.ROLL_HELP.getMessages()) {
                sender.sendMessage(message);
            }
            return true;

        }

        try {

            String[] split = args[0].split(Pattern.quote(MODIFIER_START));

            int sides = Integer.parseInt(split[0]);
            int modifier = 0;

            // Modifier present
            if(split.length > 1) {

                String modifierString = split[1];

                modifierString = modifierString.substring(0, modifierString.indexOf(MODIFIER_END));
                modifierString = modifierString.replace("+", "");

                modifier = Integer.parseInt(modifierString);

            }

            Dice dice = DiceManager.getDice(sides);

            DiceRollEvent diceRollEvent = new DiceRollEvent(dice, sender, modifier, broadcast);
            Bukkit.getPluginManager().callEvent(diceRollEvent);

            if(diceRollEvent.isCancelled()) {
                return true;
            }

            Dice eventDice = diceRollEvent.getDice();

            if(dice != eventDice) {
                dice = eventDice;
            }

            int eventModifier = diceRollEvent.getModifier();

            if(modifier != eventModifier) {
                modifier = eventModifier;
            }

            boolean eventBroadcast = diceRollEvent.isBroadcasted();

            if(broadcast != eventBroadcast) {
                broadcast = eventBroadcast;
            }

            int result = dice.roll(modifier);

            List<String> messages = new ArrayList<>();

            for(String message : (broadcast ? DiceRollaMessage.BROLL_RESULT : DiceRollaMessage.ROLL_RESULT).getMessages()) {
                messages.add(message
                        .replaceAll("%player%", sender.getName())
                        .replaceAll("%result%", result + "")
                        .replaceAll("%modifier%", modifier + "")
                );
            }

            if(broadcast) {

                for(String message : messages) {
                    Bukkit.broadcastMessage(message);
                }

            } else {

                for(String message : messages) {
                    sender.sendMessage(message);
                }

            }

        } catch(NumberFormatException | NullPointerException exception) {

            for(String message : DiceRollaMessage.INVALID_DICE.getMessages()) {
                sender.sendMessage(message);
            }

            return true;

        }

        return true;
    }

}
