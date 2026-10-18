package dev.yowsef.stealablock.api.event.lifecycle;

import dev.yowsef.stealablock.api.StealABlockAPI;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when StealABlock has finished initializing and its Developer API is ready.
 */
public final class StealABlockReadyEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final StealABlockAPI api;

    public StealABlockReadyEvent(@NotNull StealABlockAPI api) {
        this.api = api;
    }

    public @NotNull StealABlockAPI getApi() {
        return api;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
