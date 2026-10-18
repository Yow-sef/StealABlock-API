package dev.yowsef.stealablock.api.event.steal;

import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Fired when an active steal countdown or stolen block carry is cancelled and/or returned to the victim.
 */
public final class StealCancelEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player thief;
    private final @Nullable UUID victimId;
    private final @Nullable BlockType returnedBlock;

    public StealCancelEvent(@NotNull Player thief, @Nullable UUID victimId,
                            @Nullable BlockType returnedBlock, @NotNull Cause cause) {
        super(cause);
        this.thief = thief;
        this.victimId = victimId;
        this.returnedBlock = returnedBlock;
    }

    public @NotNull Player getThief() {
        return thief;
    }

    public @Nullable UUID getVictimId() {
        return victimId;
    }

    public @Nullable BlockType getReturnedBlock() {
        return returnedBlock;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
