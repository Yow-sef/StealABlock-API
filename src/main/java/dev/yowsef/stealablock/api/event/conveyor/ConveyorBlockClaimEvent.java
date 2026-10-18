package dev.yowsef.stealablock.api.event.conveyor;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.conveyor.ConveyorBlockView;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a player claims a moving block from the conveyor.
 */
public final class ConveyorBlockClaimEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final ConveyorBlockView conveyorBlock;

    public ConveyorBlockClaimEvent(@NotNull UUID playerId, @NotNull ConveyorBlockView conveyorBlock) {
        super(Cause.GAMEPLAY);
        this.playerId = playerId;
        this.conveyorBlock = conveyorBlock;
    }

    public @NotNull UUID getPlayerId() {
        return playerId;
    }

    public @NotNull ConveyorBlockView getConveyorBlock() {
        return conveyorBlock;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
