package dev.yowsef.stealablock.api.event.steal;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Fired when a player attempts to begin stealing a block from a rival plot,
 * after all registered {@code StealRule}s have allowed the attempt.
 */
public final class StealAttemptEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player thief;
    private final UUID victimId;
    private final StoredBlockView targetBlock;
    private int durationSeconds;
    private @Nullable String denyMessage;

    public StealAttemptEvent(@NotNull Player thief, @NotNull UUID victimId,
                             @NotNull StoredBlockView targetBlock, int durationSeconds, @NotNull Cause cause) {
        super(cause);
        this.thief = thief;
        this.victimId = victimId;
        this.targetBlock = targetBlock;
        this.durationSeconds = durationSeconds;
    }

    public @NotNull Player getThief() {
        return thief;
    }

    public @NotNull UUID getVictimId() {
        return victimId;
    }

    public @NotNull StoredBlockView getTargetBlock() {
        return targetBlock;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = Math.max(1, durationSeconds);
    }

    public @Nullable String getDenyMessage() {
        return denyMessage;
    }

    public void deny(@Nullable String message) {
        setCancelled(true);
        this.denyMessage = message;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
