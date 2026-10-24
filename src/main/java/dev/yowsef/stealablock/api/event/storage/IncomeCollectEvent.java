package dev.yowsef.stealablock.api.event.storage;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player collects accumulated money from a stored block's pressure plate.
 */
public final class IncomeCollectEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final StoredBlockView storedBlock;
    private double amount;

    public IncomeCollectEvent(@NotNull Player player, @NotNull StoredBlockView storedBlock, double amount) {
        super(Cause.of(CauseType.INCOME_COLLECT));
        this.player = player;
        this.storedBlock = storedBlock;
        this.amount = amount;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull StoredBlockView getStoredBlock() {
        return storedBlock;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (!Double.isFinite(amount) || amount < 0.0) {
            throw new IllegalArgumentException("amount must be finite and >= 0");
        }
        this.amount = amount;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
