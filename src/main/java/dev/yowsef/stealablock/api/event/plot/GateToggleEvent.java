package dev.yowsef.stealablock.api.event.plot;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.plot.PlotView;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a plot security gate is opened or closed.
 */
public final class GateToggleEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player owner;
    private final PlotView plot;
    private final int gateId;
    private final boolean opening;

    public GateToggleEvent(@NotNull Player owner, @NotNull PlotView plot, int gateId, boolean opening, @NotNull Cause cause) {
        super(cause);
        this.owner = owner;
        this.plot = plot;
        this.gateId = gateId;
        this.opening = opening;
    }

    public @NotNull Player getOwner() {
        return owner;
    }

    public @NotNull PlotView getPlot() {
        return plot;
    }

    public int getGateId() {
        return gateId;
    }

    public boolean isOpening() {
        return opening;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
