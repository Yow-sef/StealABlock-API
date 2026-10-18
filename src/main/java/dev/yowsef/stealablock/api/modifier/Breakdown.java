package dev.yowsef.stealablock.api.modifier;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Full trace and result of evaluating a {@link ModifierType} pipeline.
 */
public record Breakdown(
        double baseValue,
        double finalValue,
        @NotNull List<Step> steps
) {
    public double totalMultiplier() {
        return baseValue == 0.0 ? 0.0 : finalValue / baseValue;
    }

    public @NotNull List<String> activeDisplayTags() {
        return steps.stream()
                .map(Step::displayTag)
                .filter(t -> t != null && !t.isEmpty())
                .toList();
    }

    /**
     * A single active modifier step that contributed to the final value.
     */
    public record Step(
            @NotNull Key key,
            @NotNull String ownerPlugin,
            @NotNull Operation operation,
            double operand,
            double valueAfter,
            @Nullable String displayTag
    ) {}
}
