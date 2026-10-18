package dev.yowsef.stealablock.api.cosmetic;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Queries and applies player armor trims and hex name colors.
 */
public interface CosmeticService {

    @NotNull List<String> getUnlockedPatterns(@NotNull UUID playerId);

    @NotNull List<String> getUnlockedMaterials(@NotNull UUID playerId);

    @NotNull List<String> getUnlockedColors(@NotNull UUID playerId);

    @Nullable String getActiveNameColor(@NotNull UUID playerId);

    boolean applyTrim(@NotNull Player player, @NotNull String patternKey, @NotNull String materialKey);

    boolean stripTrim(@NotNull Player player);
}
