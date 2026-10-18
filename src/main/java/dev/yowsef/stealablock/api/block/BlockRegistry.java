package dev.yowsef.stealablock.api.block;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Query and lookup service for all registered StealABlock block definitions.
 */
public interface BlockRegistry {

    @NotNull Optional<BlockType> get(@NotNull String id);

    @NotNull Collection<BlockType> all();

    @NotNull List<BlockType> byRarity(@NotNull String rarity);

    @NotNull Optional<BlockType> random();

    @NotNull Optional<BlockType> randomByRarity(@NotNull String rarity);

    @NotNull Optional<BlockType> randomAboveRarity(@NotNull String currentRarity);

    @NotNull Optional<BlockType> mutated(@NotNull BlockType baseOrMutated, @NotNull String mutationPrefix);

    @NotNull BlockType base(@NotNull BlockType block);

    int count();
}
