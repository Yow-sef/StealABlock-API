package dev.yowsef.stealablock.api.modifier;

import dev.yowsef.stealablock.api.Registration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Registry and evaluator for StealABlock's stackable Modifier Pipeline.
 */
public interface ModifierService {

    /**
     * Registers a modifier on the specified pipeline. Automatically unregistered when {@code owner} disables.
     */
    <C extends ModifierContext> @NotNull Registration register(
            @NotNull Plugin owner,
            @NotNull ModifierType<C> type,
            @NotNull Modifier<C> modifier
    );

    /**
     * Evaluates all registered modifiers for {@code type} against {@code context} and returns a full breakdown.
     */
    <C extends ModifierContext> @NotNull Breakdown evaluate(
            @NotNull ModifierType<C> type,
            @NotNull C context
    );

    /**
     * @return read-only view of all currently registered modifiers for {@code type}
     */
    <C extends ModifierContext> @NotNull Collection<Modifier<C>> getModifiers(@NotNull ModifierType<C> type);
}
