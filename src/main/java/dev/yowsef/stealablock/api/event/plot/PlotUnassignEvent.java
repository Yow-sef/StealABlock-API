package dev.yowsef.stealablock.api.event.plot;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import dev.yowsef.stealablock.api.plot.PlotView;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a player is unassigned from their arena plot (e.g., on quit or arena switch).
 */
public final class PlotUnassignEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final PlotView plot;

    public PlotUnassignEvent(@NotNull UUID playerId, @NotNull PlotView plot, @NotNull Cause cause) {
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
