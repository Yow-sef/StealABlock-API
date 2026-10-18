package dev.yowsef.stealablock.api.fusion;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Calculated odds for a fusion attempt after applying active events and modifiers.
 */
public record FusionOdds(
        @NotNull String sourceBlockId,
        @NotNull String currentMutationId,
        int requiredBlocks,
        double successChance,
        @NotNull Map<String, Double> mutationRollWeights
) {
}
