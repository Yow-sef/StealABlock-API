package dev.yowsef.stealablock.api.steal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Verdict returned by a {@link StealRule}.
 */
public record RuleVerdict(boolean allowed, @Nullable String denyMessage) {

    private static final RuleVerdict ALLOW = new RuleVerdict(true, null);

    public boolean denied() {
        return !allowed;
    }

    public static @NotNull RuleVerdict allow() {
        return ALLOW;
    }

    public static @NotNull RuleVerdict deny(@NotNull String reasonMessage) {
        return new RuleVerdict(false, reasonMessage);
    }
}
