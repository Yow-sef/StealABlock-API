package dev.yowsef.stealablock.api.conveyor;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.block.BlockType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Controls arena conveyors, active blocks in transit, and reward block spawns.
 */
public interface ConveyorService {

    boolean isRunning(@NotNull String arenaId);

    @MainThread
    void start(@NotNull String arenaId);

    @MainThread
    void stop(@NotNull String arenaId);

    @NotNull List<ConveyorBlockView> getBlocks(@NotNull String arenaId);

    @NotNull List<ConveyorBlockView> getAllBlocks();

    int getDeliveringCount(@NotNull UUID playerId);

    boolean isPlayerActive(@NotNull UUID playerId);

    void recordPlayerActivity(@NotNull UUID playerId);

    @MainThread
    boolean spawnRewardBlock(@NotNull Player player, @NotNull BlockType blockType);

    @MainThread
    void spawnGoalRewardBlock(@NotNull String arenaId, @NotNull String rarity);

    @MainThread
    void boostNearbyBlocks(@NotNull Location location, double radius);
}
