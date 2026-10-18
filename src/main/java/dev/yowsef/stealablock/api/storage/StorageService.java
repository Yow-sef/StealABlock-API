package dev.yowsef.stealablock.api.storage;

import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.block.BlockType;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.ActionResult;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Manages blocks stored on player plot anvils, accumulated income, selling, and upgrades.
 */
public interface StorageService {

    @NotNull List<StoredBlockView> getBlocks(@NotNull UUID playerId);

    @NotNull Optional<StoredBlockView> getBlockAtAnvil(@NotNull Location anvilLocation);

    int countBlocks(@NotNull UUID playerId, @NotNull String blockId);

    boolean isStorageFull(@NotNull Player player);

    @MainThread
    @NotNull ActionResult giveBlock(@NotNull UUID playerId, @NotNull BlockType blockType, @NotNull Cause cause);

    @MainThread
    @NotNull ActionResult storeBlockAt(@NotNull UUID playerId, @NotNull BlockType blockType, @NotNull Location anvilLoc,
                                       @NotNull String arenaId, @NotNull Cause cause);

    @MainThread
    @NotNull ActionResult removeBlock(@NotNull StoredBlockView blockView, @NotNull Cause cause);

    @MainThread
    @NotNull ActionResult upgradeBlock(@NotNull StoredBlockView blockView, @NotNull BlockType newType, @NotNull Cause cause);

    @MainThread
    @NotNull ActionResult sellBlock(@NotNull Player owner, @NotNull StoredBlockView blockView, @NotNull Cause cause);

    @MainThread
    double collectAccumulatedMoney(@NotNull Player owner, @NotNull StoredBlockView blockView, @NotNull Cause cause);
}
