package dev.yowsef.stealablock.api.event.storage;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired once per second per stored block when passive income is added (only constructed and fired
 * if at least one listener is registered to this event).
 */
public final class IncomeTickEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final StoredBlockView storedBlock;
    private double incomeAmount;

    public IncomeTickEvent(@NotNull Player player, @NotNull StoredBlockView storedBlock, double incomeAmount) {
        super(Cause.of(CauseType.INCOME_TICK));
        this.player = player;
        this.storedBlock = storedBlock;
        this.incomeAmount = incomeAmount;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull StoredBlockView getStoredBlock() {
        return storedBlock;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    public static boolean hasRegisteredListeners() {
        return HANDLERS.getRegisteredListeners().length > 0;
    }
}
