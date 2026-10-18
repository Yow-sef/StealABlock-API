package dev.yowsef.stealablock.api.progression;

import dev.yowsef.stealablock.api.Registration;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Manages stealing milestones, block discovery index, and custom milestone reward handlers.
 */
public interface MilestoneService {

    int getTotalStolen(@NotNull UUID playerId);

    void incrementTotalStolen(@NotNull UUID playerId, int delta);

    @NotNull List<String> getClaimedMilestones(@NotNull UUID playerId);

    boolean hasClaimed(@NotNull UUID playerId, @NotNull String milestoneId);

    @NotNull Set<String> getDiscoveredBlocks(@NotNull UUID playerId);

    boolean hasDiscoveredBlock(@NotNull UUID playerId, @NotNull String blockId);

    void discoverBlock(@NotNull UUID playerId, @NotNull String blockId);

    @NotNull Registration registerRewardHandler(@NotNull Plugin owner, @NotNull Key rewardTypeKey, @NotNull MilestoneRewardHandler handler);

    @FunctionalInterface
    interface MilestoneRewardHandler {
        void grant(@NotNull Player player, @NotNull String milestoneId, @NotNull String rawArgument);
    }
}
