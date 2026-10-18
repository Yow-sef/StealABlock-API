package dev.yowsef.stealablock.api.fusion;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.result.ActionResult;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * Inspects fusion eligibility, calculated odds, and triggers block fusions.
 */
public interface FusionService {

    boolean canFuse(@NotNull UUID playerId, @NotNull String blockId);

    @NotNull Optional<FusionOdds> getOdds(@NotNull UUID playerId, @NotNull String blockId);

    @MainThread
    @NotNull ActionResult startFusion(@NotNull Player player, @NotNull String blockId);
}
