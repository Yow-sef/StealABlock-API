package dev.yowsef.stealablock.api.player;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import dev.yowsef.stealablock.api.plot.PlotView;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Immutable, thread-safe snapshot of a player's full StealABlock profile.
 */
@AnyThread
public record PlayerProfile(
        @NotNull UUID uuid,
        @Nullable String name,
        double balance,
        int credits,
        int rebirthLevel,
        int prestigeLevel,
        int totalStolen,
        @Nullable PlotView plot,
        @NotNull List<StoredBlockView> storedBlocks,
        @NotNull Set<String> ownedGearIds,
        @NotNull Set<String> discoveredBlockIds,
        boolean bedrockPlayer
) {
}
