package dev.yowsef.stealablock.api.event.progression;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player is about to Prestige their base. Cancellable.
 */
public final class PlayerPrestigeEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final int previousPrestige;
    private final int newPrestige;
    private double cost;

    public PlayerPrestigeEvent(@NotNull Player player, int previousPrestige, int newPrestige, double cost, @NotNull Cause cause) {
        super(cause);
        this.player = player;
        this.previousPrestige = previousPrestige;
        this.newPrestige = newPrestige;
        this.cost = cost;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public int getPreviousPrestige() {
        return previousPrestige;
    }

    public int getNewPrestige() {
        return newPrestige;
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
