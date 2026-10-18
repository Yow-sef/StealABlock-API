package dev.yowsef.stealablock.api.event.economy;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired whenever a player's Heist Credits balance changes.
 */
public final class CreditsChangeEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final int previousCredits;
    private int newCredits;

    public CreditsChangeEvent(@NotNull UUID playerId, int previousCredits, int newCredits, @NotNull Cause cause) {
        super(cause);
        this.playerId = playerId;
        this.previousCredits = previousCredits;
        this.newCredits = newCredits;
    }

    public @NotNull UUID getPlayerId() {
        return playerId;
    }

    public int getPreviousCredits() {
        return previousCredits;
    }

    public int getNewCredits() {
        return newCredits;
    }

    public void setNewCredits(int newCredits) {
        this.newCredits = Math.max(0, newCredits);
    }

    public int getDelta() {
        return newCredits - previousCredits;
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
