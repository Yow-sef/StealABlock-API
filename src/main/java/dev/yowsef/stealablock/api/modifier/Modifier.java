package dev.yowsef.stealablock.api.modifier;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A prioritized calculation step in a {@link ModifierType} pipeline.
 *
 * @param <C> context type
 */
public interface Modifier<C extends ModifierContext> {

    /**
     * @return unique namespaced key of this modifier (e.g. {@code Key.key("myaddon", "vip_boost")})
     */
    @NotNull Key key();

    /**
     * @return priority ordering (lower runs earlier; default is {@code 0})
     */
    default int priority() {
        return 0;
    }

    /**
     * @return mathematical operation to perform
     */
    @NotNull Operation operation();

    /**
     * Evaluates the modifier's operand for the given context.
     * Returning the identity value ({@code 0.0} for {@link Operation#ADD}, {@code 1.0} for {@link Operation#MULTIPLY},
     * or {@link Double#NaN} for any operation) skips this modifier for the current evaluation.
     */
    double evaluate(@NotNull C context);

    /**
     * Optional legacy/colorized badge tag (e.g. {@code "&#FFAA00[VIP]"}) to display on rate holograms
     * and in {@code /sab api} breakdowns when this modifier actively contributes.
     */
    default @Nullable String displayTag(@NotNull C context) {
        return null;
    }
}
