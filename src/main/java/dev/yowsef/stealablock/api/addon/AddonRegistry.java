package dev.yowsef.stealablock.api.addon;

import dev.yowsef.stealablock.api.Registration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Tracks third-party plugins that integrate with StealABlock.
 */
public interface AddonRegistry {

    /**
     * Explicitly registers an addon callback listener owned by {@code plugin}.
     */
    @NotNull Registration register(@NotNull Plugin plugin, @NotNull StealABlockAddon addon);

    /**
     * @return names of all plugins that currently hold active registrations or addon hooks
     */
    @NotNull Collection<String> getActiveAddonPlugins();

    /**
     * Unregisters all hooks, modifiers, rules, and custom types owned by {@code plugin}.
     *
     * @return number of registrations removed
     */
    int unregisterAll(@NotNull Plugin plugin);
}
