package shwendel.dicerolla.dice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastRollCommandExecutor implements CommandExecutor {

    private static final String PERMISSION = "dicerolla.broadcast";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        RollCommand.onCommand(sender, command, label, args, true);

        return true;
    }

}

