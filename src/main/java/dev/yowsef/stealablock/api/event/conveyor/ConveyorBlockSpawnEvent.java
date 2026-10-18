package dev.yowsef.stealablock.api.event.conveyor;

import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a block is about to spawn on an arena conveyor.
 * Addons may replace the {@link BlockType}, toggle mystery block status, or cancel the spawn.
 */
public final class ConveyorBlockSpawnEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final String arenaId;
    private BlockType blockType;
    private boolean mysteryBlock;

    public ConveyorBlockSpawnEvent(@NotNull String arenaId, @NotNull BlockType blockType, boolean mysteryBlock) {
        super(Cause.GAMEPLAY);
        this.arenaId = arenaId;
        this.blockType = blockType;
        this.mysteryBlock = mysteryBlock;
    }

    public @NotNull String getArenaId() {
        return arenaId;
    }

    public @NotNull BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(@NotNull BlockType blockType) {
        this.blockType = blockType;
    }

    public boolean isMysteryBlock() {
        return mysteryBlock;
    }

    public void setMysteryBlock(boolean mysteryBlock) {
        this.mysteryBlock = mysteryBlock;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
