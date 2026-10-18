package dev.yowsef.stealablock.api.progression;

import dev.yowsef.stealablock.api.Registration;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Manages the Heist Board daily contracts and custom contract types.
 */
public interface ContractService {

    record ContractView(
            @NotNull String typeId,
            int tier,
            long target,
            int rewardCredits,
            double rewardMoney,
            boolean completed,
            boolean claimed
    ) {}

    record CustomContractType(
            @NotNull String id,
            @NotNull String displayName,
            @NotNull Material icon,
            @NotNull String unitDescription,
            @NotNull String hintDescription
    ) {}

    @NotNull List<ContractView> getBoard(@NotNull UUID playerId);

    int getActiveSlot(@NotNull UUID playerId);

    long getActiveProgress(@NotNull UUID playerId);

    boolean setActiveSlot(@NotNull UUID playerId, int slotIndex);

    void recordProgress(@NotNull UUID playerId, @NotNull String contractTypeId, long amount);

    boolean claim(@NotNull UUID playerId, int slotIndex);

    boolean rerollBoard(@NotNull UUID playerId);

    @NotNull Registration registerContractType(@NotNull Plugin owner, @NotNull CustomContractType customType);
}
