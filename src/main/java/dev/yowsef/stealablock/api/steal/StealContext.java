package dev.yowsef.stealablock.api.steal;

import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Context provided to {@link StealRule}s when a player attempts to steal a rival's block.
 */
public record StealContext(
        @NotNull Player thief,
        @NotNull UUID victimId,
        @NotNull StoredBlockView targetBlock,
        @NotNull String arenaId,
        int plotNumber
) {
}
