package dev.yowsef.stealablock.api.gameevent;

import dev.yowsef.stealablock.api.Registration;
import dev.yowsef.stealablock.api.annotation.MainThread;
import net.kyori.adventure.key.Key;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Controls server-wide events (Double Income, Shield Hour, Overdrive, and custom addon events).
 */
public interface GameEventService {

    record ActiveGameEvent(
            @NotNull Key key,
            @NotNull String displayName,
            int secondsRemaining,
            int totalDurationSeconds
    ) {}

    boolean isEventActive();

    @NotNull Optional<ActiveGameEvent> getActiveEvent();

    @MainThread
    boolean startEvent(@NotNull Key eventKey, int durationSeconds);

    @MainThread
    void stopActiveEvent(boolean announce);

    @NotNull Registration registerEventType(@NotNull Plugin owner, @NotNull GameEventType eventType);

    @NotNull Optional<GameEventType> getEventType(@NotNull Key eventKey);

    @NotNull Collection<GameEventType> getRegisteredTypes();
}
