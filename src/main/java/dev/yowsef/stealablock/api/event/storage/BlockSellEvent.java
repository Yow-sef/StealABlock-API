package dev.yowsef.stealablock.api.event.storage;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player sells one of their stored blocks.
 */
public final class BlockSellEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final StoredBlockView storedBlock;
    private double totalPayout;

    public BlockSellEvent(@NotNull Player player, @NotNull StoredBlockView storedBlock, double totalPayout, @NotNull Cause cause) {
        super(cause);
        this.player = player;
        this.storedBlock = storedBlock;
        this.totalPayout = totalPayout;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull StoredBlockView getStoredBlock() {
        return storedBlock;
    }

    public double getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(double totalPayout) {
        this.totalPayout = totalPayout;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
