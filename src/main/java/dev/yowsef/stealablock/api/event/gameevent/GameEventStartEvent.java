package dev.yowsef.stealablock.api.event.gameevent;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import net.kyori.adventure.key.Key;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a server-wide Game Event is about to start.
 */
public final class GameEventStartEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Key eventKey;
    private int durationSeconds;

    public GameEventStartEvent(@NotNull Key eventKey, int durationSeconds) {
        super(Cause.of(CauseType.GAME_EVENT, eventKey.asString()));
        this.eventKey = eventKey;
        this.durationSeconds = durationSeconds;
    }

    public @NotNull Key getEventKey() {
        return eventKey;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = Math.max(1, durationSeconds);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
