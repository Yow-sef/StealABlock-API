package dev.yowsef.stealablock.api.conveyor;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import dev.yowsef.stealablock.api.block.BlockType;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Immutable snapshot of a block currently moving on a conveyor or being delivered to a plot.
 */
@AnyThread
public record ConveyorBlockView(
        @NotNull String arenaId,
        @NotNull BlockType blockType,
        @NotNull String state,
        boolean mysteryBlock,
        @Nullable UUID claimedOwnerId,
        int targetPlotNumber,
        double speedMultiplier,
        @Nullable Location currentLocation
) {
}
