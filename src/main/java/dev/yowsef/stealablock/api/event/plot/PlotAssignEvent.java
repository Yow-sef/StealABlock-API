package dev.yowsef.stealablock.api.event.plot;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.plot.PlotView;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a player is about to be assigned to an arena plot.
 */
public final class PlotAssignEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final PlotView plot;

    public PlotAssignEvent(@NotNull UUID playerId, @NotNull PlotView plot, @NotNull Cause cause) {
        super(cause);
        this.playerId = playerId;
        this.plot = plot;
    }

    public @NotNull UUID getPlayerId() {
        return playerId;
    }

    public @NotNull PlotView getPlot() {
        return plot;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
