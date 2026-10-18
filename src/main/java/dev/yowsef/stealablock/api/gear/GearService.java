package dev.yowsef.stealablock.api.gear;

import dev.yowsef.stealablock.api.Registration;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Manages player gear items, tiers, and custom gear abilities.
 */
public interface GearService {

    record GearView(
            @NotNull String id,
            @NotNull String baseId,
            @NotNull String displayName,
            @NotNull Material material,
            int tier,
            double price,
            @NotNull List<String> abilityLines
    ) {}

    interface GearAbility {
        @NotNull Key key();
        default void onUse(@NotNull Player player) {}
        default void onAttack(@NotNull Player attacker, @NotNull Player target) {}
    }

    @NotNull Set<String> getOwnedGearIds(@NotNull UUID playerId);

    boolean hasGear(@NotNull UUID playerId, @NotNull String gearId);

    void grantGear(@NotNull UUID playerId, @NotNull String baseId, @NotNull String gearId);

    @NotNull Optional<GearView> getDefinition(@NotNull String gearId);

    @NotNull Collection<GearView> getAllDefinitions();

    @NotNull Registration registerAbility(@NotNull Plugin owner, @NotNull GearAbility ability);
}
