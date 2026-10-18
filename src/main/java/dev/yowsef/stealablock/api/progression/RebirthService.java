package dev.yowsef.stealablock.api.progression;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.ActionResult;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Queries and manages player Rebirth levels, multipliers, and costs.
 */
public interface RebirthService {

    int getMaxLevel();

    int getRebirthLevel(@NotNull UUID playerId);

    void setRebirthLevel(@NotNull UUID playerId, int level, @NotNull Cause cause);

    double getNextRebirthCost(@NotNull UUID playerId);

    double getMoneyMultiplier(int rebirthLevel);

    double getStartingCash(int rebirthLevel);

    int getGateLockDurationSeconds(int rebirthLevel);

    boolean hasRequiredRareBlock(@NotNull UUID playerId);

    @NotNull ActionResult canRebirth(@NotNull Player player);

    @MainThread
    @NotNull ActionResult executeRebirth(@NotNull Player player, @NotNull Cause cause);
}
