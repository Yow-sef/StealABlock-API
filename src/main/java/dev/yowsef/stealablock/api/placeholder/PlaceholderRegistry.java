package dev.yowsef.stealablock.api.placeholder;

import dev.yowsef.stealablock.api.Registration;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Allows addons to register sub-placeholders under {@code %stealablock_<key>%} without needing
 * their own PlaceholderAPI expansion.
 */
public interface PlaceholderRegistry {

    @FunctionalInterface
    interface PlaceholderResolver {
        @Nullable String resolve(@Nullable OfflinePlayer player, @NotNull String params);
    }

    /**
     * Registers a custom placeholder prefix under {@code %stealablock_<prefix>_%}.
     */
    @NotNull Registration register(@NotNull Plugin owner, @NotNull String prefix, @NotNull PlaceholderResolver resolver);

    @Nullable String resolve(@Nullable OfflinePlayer player, @NotNull String params);
}
