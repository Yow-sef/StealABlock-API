package dev.yowsef.stealablock.api.event.fusion;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player initiates a block fusion in {@code /fusion}.
 */
public final class FusionStartEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String sourceBlockId;
    private final String currentMutationId;

    public FusionStartEvent(@NotNull Player player, @NotNull String sourceBlockId, @NotNull String currentMutationId) {
        super(Cause.of(CauseType.BLOCK_FUSION));
        this.player = player;
        this.sourceBlockId = sourceBlockId;
        this.currentMutationId = currentMutationId;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull String getSourceBlockId() {
        return sourceBlockId;
    }

    public @NotNull String getCurrentMutationId() {
        return currentMutationId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
