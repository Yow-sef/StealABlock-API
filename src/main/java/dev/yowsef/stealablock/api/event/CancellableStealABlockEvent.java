package dev.yowsef.stealablock.api.event;

import dev.yowsef.stealablock.api.cause.Cause;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for cancellable StealABlock Bukkit events.
 */
public abstract class CancellableStealABlockEvent extends StealABlockEvent implements Cancellable {

    private boolean cancelled;

    protected CancellableStealABlockEvent() {
        super();
    }

    protected CancellableStealABlockEvent(@NotNull Cause cause) {
        super(cause);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
