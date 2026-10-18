package dev.yowsef.stealablock.api.event.storage;

import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.cause.CauseType;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when a mystery block finishes unboxing on a player's anvil and rolls its mutation.
 */
public final class MysteryBlockUnboxEvent extends StealABlockEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Location anvilLocation;
    private BlockType unboxedBlock;
    private String mutationId;

    public MysteryBlockUnboxEvent(@NotNull Player player, @NotNull Location anvilLocation,
                                  @NotNull BlockType unboxedBlock, @NotNull String mutationId) {
        super(Cause.of(CauseType.MYSTERY_UNBOX));
        this.player = player;
        this.anvilLocation = anvilLocation;
        this.unboxedBlock = unboxedBlock;
        this.mutationId = mutationId;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public @NotNull Location getAnvilLocation() {
        return anvilLocation;
    }

    public @NotNull BlockType getUnboxedBlock() {
        return unboxedBlock;
    }

    public void setUnboxedBlock(@NotNull BlockType unboxedBlock) {
        this.unboxedBlock = unboxedBlock;
    }

    public @NotNull String getMutationId() {
        return mutationId;
    }

    public void setMutationId(@NotNull String mutationId) {
        this.mutationId = mutationId;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
