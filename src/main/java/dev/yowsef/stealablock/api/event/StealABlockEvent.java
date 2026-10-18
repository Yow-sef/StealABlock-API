package dev.yowsef.stealablock.api.event;

import dev.yowsef.stealablock.api.cause.Cause;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for all StealABlock Bukkit events.
 */
public abstract class StealABlockEvent extends Event {

    private final Cause cause;

    protected StealABlockEvent() {
        this(Cause.GAMEPLAY, false);
    }

    protected StealABlockEvent(@NotNull Cause cause) {
        this(cause, false);
    }

    protected StealABlockEvent(@NotNull Cause cause, boolean async) {
        super(async);
        this.cause = cause;
    }

    public @NotNull Cause getCause() {
        return cause;
    }
}
