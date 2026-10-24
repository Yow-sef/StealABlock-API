package dev.yowsef.stealablock.api.progression;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Manages the Heist Board daily contracts. Contract types are the built-in
 * {@code STEAL}, {@code EARN}, {@code DEPOSIT}, and {@code FUSE} jobs.
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

    @NotNull List<ContractView> getBoard(@NotNull UUID playerId);

    int getActiveSlot(@NotNull UUID playerId);

    long getActiveProgress(@NotNull UUID playerId);

    boolean setActiveSlot(@NotNull UUID playerId, int slotIndex);

    void recordProgress(@NotNull UUID playerId, @NotNull String contractTypeId, long amount);

    boolean claim(@NotNull UUID playerId, int slotIndex);

    boolean rerollBoard(@NotNull UUID playerId);
}
