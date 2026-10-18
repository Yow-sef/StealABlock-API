package dev.yowsef.stealablock.api;

import net.kyori.adventure.key.Key;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Handle returned when registering a modifier, rule, custom event, mutation, provider, or handler.
 * Calling {@link #unregister()} or {@link #close()} removes the registration immediately.
 * All registrations are also automatically removed when the owning {@link Plugin} is disabled.
 */
public interface Registration extends AutoCloseable {

    /**
     * @return the plugin that owns this registration
     */
    @NotNull Plugin owner();

    /**
     * @return the namespaced key of this registration
     */
    @NotNull Key key();

    /**
     * @return true if this registration is still active
     */
    boolean isActive();

    /**
     * Removes this registration from StealABlock. Safe to call multiple times.
     */
    void unregister();

    @Override
    default void close() {
        unregister();
    }
}
