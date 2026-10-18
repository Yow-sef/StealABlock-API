package dev.yowsef.stealablock.api.event.progression;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Fired when a player progresses or claims a Heist Board contract.
 */
public final class ContractProgressEvent extends CancellableStealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerId;
    private final String contractTypeId;
    private long delta;

    public ContractProgressEvent(@NotNull UUID playerId, @NotNull String contractTypeId, long delta) {
        super(Cause.of(CauseType.CONTRACT_REWARD, contractTypeId));
        this.playerId = playerId;
        this.contractTypeId = contractTypeId;
        this.delta = delta;
    }

    public @NotNull UUID getPlayerId() {
        return playerId;
    }

    public @NotNull String getContractTypeId() {
        return contractTypeId;
    }

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = Math.max(0L, delta);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
