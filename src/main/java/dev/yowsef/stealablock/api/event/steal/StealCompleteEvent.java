package dev.yowsef.stealablock.api.event.steal;

import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a thief finishes the steal countdown and picks up the victim's block.
 */
public final class StealCompleteEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player thief;
    private final UUID victimId;
    private final BlockType stolenBlock;

    public StealCompleteEvent(@NotNull Player thief, @NotNull UUID victimId,
                              @NotNull BlockType stolenBlock, @NotNull Cause cause) {
        super(cause);
        this.thief = thief;
        this.victimId = victimId;
        this.stolenBlock = stolenBlock;
    }

    public @NotNull Player getThief() {
        return thief;
    }

    public @NotNull UUID getVictimId() {
        return victimId;
    }

    public @NotNull BlockType getStolenBlock() {
        return stolenBlock;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
