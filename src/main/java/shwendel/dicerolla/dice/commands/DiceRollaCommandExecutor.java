package shwendel.dicerolla.dice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import shwendel.dicerolla.DiceRolla;
import shwendel.dicerolla.dice.DiceManager;
import shwendel.dicerolla.messages.DiceRollaMessage;
import shwendel.dicerolla.messages.DiceRollaMessageManager;

public class DiceRollaCommandExecutor implements CommandExecutor {

    private static final String PERMISSION = "dicerolla.reload";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {

            sendHelpMessage(sender);
            return true;

        }

        if(!sender.hasPermission(PERMISSION)) {

            for(String message : DiceRollaMessage.NO_PERMISSION.getMessages()) {
                sender.sendMessage(message);
            }

        }

        switch(args[0].toLowerCase()) {

            case "reload":

                DiceRolla.getInstance().reloadConfig();
                DiceManager.reload();
                DiceRollaMessageManager.reload();

                for(String message : DiceRollaMessage.RELOAD_CONFIG.getMessages()) {
                    sender.sendMessage(message);
                }

                break;

            default:

                sendHelpMessage(sender);
                break;

        }

        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        for(String message : DiceRollaMessage.DICEROLLA_HELP.getMessages()) {
            sender.sendMessage(message);
        }
    }

}
