package dev.yowsef.stealablock.api.steal;

import dev.yowsef.stealablock.api.block.BlockType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Snapshot of an active steal countdown or a thief carrying a stolen block.
 */
public record StealSession(
        @NotNull UUID thiefId,
        @NotNull UUID victimId,
        @NotNull BlockType blockType,
        boolean carrying,
        @Nullable String arenaId
) {
}
