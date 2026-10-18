package dev.yowsef.stealablock.api.event.progression;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a player claims a stealing milestone reward.
 */
public final class MilestoneClaimEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String milestoneId;

    public MilestoneClaimEvent(@NotNull Player player, @NotNull String milestoneId) {
        super(Cause.of(CauseType.MILESTONE_REWARD, milestoneId));
        this.player = player;
        this.milestoneId = milestoneId;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull String getMilestoneId() {
        return milestoneId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
