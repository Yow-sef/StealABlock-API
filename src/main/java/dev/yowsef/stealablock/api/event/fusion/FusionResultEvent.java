package dev.yowsef.stealablock.api.event.fusion;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Fired when a fusion animation completes and the outcome (fail or upgraded mutation) is resolved.
 * Listeners may override the resulting mutation or success state.
 */
public final class FusionResultEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String baseBlockId;
    private boolean succeeded;
    private @Nullable String resultMutationId;

    public FusionResultEvent(@NotNull Player player, @NotNull String baseBlockId,
                             boolean succeeded, @Nullable String resultMutationId) {
        super(Cause.of(CauseType.BLOCK_FUSION));
        this.player = player;
        this.baseBlockId = baseBlockId;
        this.succeeded = succeeded;
        this.resultMutationId = resultMutationId;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull String getBaseBlockId() {
        return baseBlockId;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public @Nullable String getResultMutationId() {
        return resultMutationId;
    }

    public void setResultMutationId(@Nullable String resultMutationId) {
        this.resultMutationId = resultMutationId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
