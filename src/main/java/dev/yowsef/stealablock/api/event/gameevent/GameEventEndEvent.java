package dev.yowsef.stealablock.api.event.gameevent;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import net.kyori.adventure.key.Key;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a server-wide Game Event ends or is stopped.
 */
public final class GameEventEndEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Key eventKey;
    private final boolean naturalExpiry;

    public GameEventEndEvent(@NotNull Key eventKey, boolean naturalExpiry) {
        super(Cause.of(CauseType.GAME_EVENT, eventKey.asString()));
        this.eventKey = eventKey;
        this.naturalExpiry = naturalExpiry;
    }

    public @NotNull Key getEventKey() {
        return eventKey;
    }

    public boolean isNaturalExpiry() {
        return naturalExpiry;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
