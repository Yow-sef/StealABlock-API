package dev.yowsef.stealablock.api.event.storage;

import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a block is about to be stored on a player's anvil (from conveyor delivery, steal deposit, or API).
 */
public final class BlockDepositEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID ownerId;
    private final String arenaId;
    private final Location anvilLocation;
    private BlockType blockType;

    public BlockDepositEvent(@NotNull UUID ownerId, @NotNull String arenaId, @NotNull Location anvilLocation,
                             @NotNull BlockType blockType, @NotNull Cause cause) {
        super(cause);
        this.ownerId = ownerId;
        this.arenaId = arenaId;
        this.anvilLocation = anvilLocation;
        this.blockType = blockType;
    }

    public @NotNull UUID getOwnerId() {
        return ownerId;
    }

    public @NotNull String getArenaId() {
        return arenaId;
    }

    public @NotNull Location getAnvilLocation() {
        return anvilLocation;
    }

    public @NotNull BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(@NotNull BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
