package dev.yowsef.stealablock.api.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Definition of a block mutation tier (built-in: normal, golden, diamond, rainbow; extensible by addons).
 */
public record Mutation(
        @NotNull String id,
        @NotNull String prefix,
        @NotNull String displayName,
        @NotNull String hexColor,
        double incomeMultiplier,
        double mysteryUnboxChance,
        int tierOrder,
        @Nullable String nextMutationId
) {
    public static final Mutation NORMAL = new Mutation("normal", "", "Normal", "&#FFFFFF", 1.0, 0.65, 0, "golden");
    public static final Mutation GOLDEN = new Mutation("golden", "golden_", "Golden", "&#FFAA00", 2.0, 0.25, 1, "diamond");
    public static final Mutation DIAMOND = new Mutation("diamond", "diamond_", "Diamond", "&#55FFFF", 4.0, 0.08, 2, "rainbow");
    public static final Mutation RAINBOW = new Mutation("rainbow", "rainbow_", "Rainbow", "&#FF55FF", 10.0, 0.02, 3, null);
}
