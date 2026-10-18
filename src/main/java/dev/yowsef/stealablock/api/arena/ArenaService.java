package dev.yowsef.stealablock.api.arena;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Queries arenas, availability, and validation status.
 */
public interface ArenaService {

    @NotNull Optional<ArenaView> get(@NotNull String arenaId);

    @NotNull Collection<ArenaView> all();

    @NotNull Optional<ArenaView> at(@NotNull Location location);

    boolean isEnabled(@NotNull String arenaId);

    @NotNull List<String> validate(@NotNull String arenaId);
}
