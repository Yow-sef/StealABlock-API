package dev.yowsef.stealablock.api.steal;

import org.jetbrains.annotations.NotNull;

/**
 * Pluggable rule evaluated before a player can begin stealing a block (e.g., team/clan protection,
 * newbie grace period, duel rules).
 */
@FunctionalInterface
public interface StealRule {

    @NotNull RuleVerdict test(@NotNull StealContext context);
}
