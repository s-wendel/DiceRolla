package shwendel.dicerolla;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import shwendel.dicerolla.dice.commands.DiceRollaCommandExecutor;
import shwendel.dicerolla.dice.commands.RollCommandExecutor;
import shwendel.dicerolla.dice.tab_completer.DiceRollaCommandTabCompleter;
import shwendel.dicerolla.dice.tab_completer.RollCommandTabCompleter;

public final class DiceRolla extends JavaPlugin {

    private static DiceRolla instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        instance = this;

        PluginCommand rollCommand = getCommand("roll");

        rollCommand.setExecutor(new RollCommandExecutor());
        rollCommand.setTabCompleter(new RollCommandTabCompleter());

        PluginCommand broadcastRollCommand = getCommand("broll");

        broadcastRollCommand.setExecutor(new RollCommandExecutor());
        broadcastRollCommand.setTabCompleter(new RollCommandTabCompleter());

        PluginCommand diceRollaCommand = getCommand("dicerolla");

        diceRollaCommand.setExecutor(new DiceRollaCommandExecutor());
        diceRollaCommand.setTabCompleter(new DiceRollaCommandTabCompleter());

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static DiceRolla getInstance() {
        return instance;
    }

}
