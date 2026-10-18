package dev.yowsef.stealablock.api.event.defense;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player deploys a Slime Trap or defense item in their plot.
 */
public final class TrapDeployEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player owner;
    private final Location location;

    public TrapDeployEvent(@NotNull Player owner, @NotNull Location location) {
        super(Cause.GAMEPLAY);
        this.owner = owner;
        this.location = location;
    }

    public @NotNull Player getOwner() {
        return owner;
    }

    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
