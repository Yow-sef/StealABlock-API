package dev.yowsef.stealablock.api.event.economy;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired whenever a player's money balance changes.
 */
public final class BalanceChangeEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final double previousBalance;
    private double newBalance;

    public BalanceChangeEvent(@NotNull UUID playerId, double previousBalance, double newBalance, @NotNull Cause cause) {
        super(cause);
        this.playerId = playerId;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
    }

    public @NotNull UUID getPlayerId() {
        return playerId;
    }

    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        if (!Double.isFinite(newBalance)) {
            throw new IllegalArgumentException("newBalance must be finite");
        }
        this.newBalance = Math.max(0.0, newBalance);
    }

    public double getDelta() {
        return newBalance - previousBalance;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    public static boolean canFireSync() {
        return Bukkit.isPrimaryThread() && HANDLERS.getRegisteredListeners().length > 0;
    }
}
