package dev.yowsef.stealablock.api.cause;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Describes who and what triggered a state change in StealABlock.
 */
public record Cause(
        @NotNull CauseType type,
        @Nullable String pluginName,
        @Nullable String detail
) {
    public static final Cause GAMEPLAY = new Cause(CauseType.GAMEPLAY, "stealablock", null);
    public static final Cause ADMIN = new Cause(CauseType.ADMIN_COMMAND, "stealablock", "admin");
    public static final Cause VAULT = new Cause(CauseType.VAULT, "Vault", "Vault Economy Bridge");

    public Cause {
        Objects.requireNonNull(type, "type");
    }

    public static @NotNull Cause of(@NotNull CauseType type) {
        return new Cause(type, "stealablock", null);
    }

    public static @NotNull Cause of(@NotNull CauseType type, @Nullable String detail) {
        return new Cause(type, "stealablock", detail);
    }

    public static @NotNull Cause plugin(@NotNull Plugin plugin) {
        return new Cause(CauseType.PLUGIN_API, plugin.getName(), null);
    }

    public static @NotNull Cause plugin(@NotNull Plugin plugin, @Nullable String detail) {
        return new Cause(CauseType.PLUGIN_API, plugin.getName(), detail);
    }

    @Override
    public @NotNull String toString() {
        return type.name()
                + (pluginName != null ? "[" + pluginName + "]" : "")
                + (detail != null ? "(" + detail + ")" : "");
    }
}
