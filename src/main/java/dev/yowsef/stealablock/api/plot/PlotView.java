package dev.yowsef.stealablock.api.plot;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import dev.yowsef.stealablock.api.storage.AnvilSlotView;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Immutable snapshot of a plot within an arena.
 */
@AnyThread
public record PlotView(
        @NotNull String arenaId,
        int number,
        boolean occupied,
        @Nullable UUID ownerId,
        @Nullable Location spawn,
        @Nullable Location pos1,
        @Nullable Location pos2,
        @NotNull List<AnvilSlotView> storageSlots,
        int rotation
) {
}
