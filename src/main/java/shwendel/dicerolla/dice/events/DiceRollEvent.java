package shwendel.dicerolla.dice.events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import shwendel.dicerolla.dice.Dice;

public class DiceRollEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private Dice dice;
    private CommandSender sender;
    private int modifier;
    private boolean broadcast;
    private boolean isCancelled;

    public DiceRollEvent(Dice dice, CommandSender sender, int modifier, boolean broadcast) {
        this.dice = dice;
        this.sender = sender;
        this.modifier = modifier;
        this.broadcast = broadcast;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public CommandSender getCommandSender() {
        return sender;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public boolean isBroadcasted() {
        return broadcast;
    }

    public void setBroadcasted(boolean broadcast) {
        this.broadcast = broadcast;
    }

}