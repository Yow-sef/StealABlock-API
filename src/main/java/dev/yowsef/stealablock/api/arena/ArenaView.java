package dev.yowsef.stealablock.api.arena;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import dev.yowsef.stealablock.api.plot.PlotView;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Immutable snapshot of an arena and its plots.
 */
@AnyThread
public record ArenaView(
        @NotNull String id,
        @Nullable String worldName,
        boolean enabled,
        boolean conveyorValid,
        @Nullable Location pos1,
        @Nullable Location pos2,
        @Nullable Location conveyorStart,
        @Nullable Location conveyorEnd,
        @NotNull List<PlotView> plots
) {
    public int availablePlotCount() {
        int c = 0;
        for (PlotView p : plots) {
            if (!p.occupied()) c++;
        }
        return c;
    }

    public int occupiedPlotCount() {
        return plots.size() - availablePlotCount();
    }
}
