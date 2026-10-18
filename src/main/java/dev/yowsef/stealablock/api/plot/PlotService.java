package dev.yowsef.stealablock.api.plot;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.result.ActionResult;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Queries and manages player plot assignments and spatial checks.
 */
public interface PlotService {

    @NotNull Optional<PlotView> getPlot(@NotNull UUID ownerId);

    @NotNull Optional<PlotView> getPlot(@NotNull String arenaId, int plotNumber);

    @NotNull Optional<PlotView> getPlotAt(@NotNull Location location);

    boolean isLocationInPlot(@NotNull Location location, @NotNull PlotView plot);

    @MainThread
    @NotNull ActionResult assignPlayer(@NotNull String arenaId, @NotNull UUID playerId);

    @MainThread
    void unassignPlayer(@NotNull UUID playerId);

    @MainThread
    void kickVisitors(@NotNull PlotView plot);
}
