package dev.yowsef.stealablock.api.gameevent;

import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Definition of a built-in or addon-registered server-wide Game Event.
 */
public record GameEventType(
        @NotNull Key key,
        @NotNull String displayName,
        @NotNull String description,
        @NotNull Material icon,
        int defaultDurationSeconds,
        @NotNull LifecycleHandler lifecycleHandler
) {
    public interface LifecycleHandler {
        default void onStart(int durationSeconds) {}
        default void onTick(int secondsRemaining) {}
        default void onStop() {}
    }

    public static @NotNull GameEventType simple(@NotNull Key key, @NotNull String displayName,
                                                @NotNull String description, @NotNull Material icon,
                                                int defaultDurationSeconds) {
        return new GameEventType(key, displayName, description, icon, defaultDurationSeconds, new LifecycleHandler() {});
    }
}
