package dev.yowsef.stealablock.api.block;

import dev.yowsef.stealablock.api.Registration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Registry of block mutations (normal, golden, diamond, rainbow, and custom addon mutations).
 */
public interface MutationRegistry {

    @NotNull Registration register(@NotNull Plugin owner, @NotNull Mutation mutation);

    @NotNull Optional<Mutation> get(@NotNull String id);

    @NotNull Mutation detect(@NotNull String blockId);

    @NotNull Collection<Mutation> all();
}
