package dev.yowsef.stealablock.api.storage;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable view of a paired anvil + pressure plate storage slot inside a plot.
 */
@AnyThread
public record AnvilSlotView(
        @NotNull Location anvilLocation,
        @Nullable Location plateLocation,
        boolean occupied,
        @Nullable StoredBlockView storedBlock
) {
}
