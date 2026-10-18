package dev.yowsef.stealablock.api.storage;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import dev.yowsef.stealablock.api.block.BlockType;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Immutable snapshot of a block stored on a player's plot anvil.
 */
@AnyThread
public record StoredBlockView(
        @NotNull BlockType blockType,
        @NotNull UUID ownerId,
        @NotNull String arenaId,
        int plotNumber,
        @NotNull Location anvilLocation,
        @Nullable Location pressurePlateLocation,
        double accumulatedMoney,
        boolean vault
) {
}
