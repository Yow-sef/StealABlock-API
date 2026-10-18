package dev.yowsef.stealablock.api.progression;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.ActionResult;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Queries and manages player Prestige levels, schematic upgrades, and costs.
 */
public interface PrestigeService {

    int getPrestigeLevel(@NotNull UUID playerId);

    void setPrestigeLevel(@NotNull UUID playerId, int level, @NotNull Cause cause);

    int getRequiredRebirths(int currentPrestige);

    double getRequiredMoney(@NotNull UUID playerId, int currentPrestige);

    boolean hasGoatBlock(@NotNull Player player);

    @NotNull ActionResult canPrestige(@NotNull Player player);

    @MainThread
    @NotNull ActionResult executePrestige(@NotNull Player player, @NotNull Cause cause);
}
