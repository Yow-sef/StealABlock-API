package dev.yowsef.stealablock.api.event.progression;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player is about to Rebirth. Cancellable.
 */
public final class PlayerRebirthEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final int previousLevel;
    private final int newLevel;
    private double cost;

    public PlayerRebirthEvent(@NotNull Player player, int previousLevel, int newLevel, double cost, @NotNull Cause cause) {
        super(cause);
        this.player = player;
        this.previousLevel = previousLevel;
        this.newLevel = newLevel;
        this.cost = cost;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public int getPreviousLevel() {
        return previousLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = Math.max(0.0, cost);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
