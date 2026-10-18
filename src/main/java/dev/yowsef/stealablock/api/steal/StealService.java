package dev.yowsef.stealablock.api.steal;

import dev.yowsef.stealablock.api.Registration;
import dev.yowsef.stealablock.api.annotation.MainThread;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.ActionResult;
import dev.yowsef.stealablock.api.storage.StoredBlockView;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Controls active steals, thief carrying state, and the pluggable {@link StealRule} arbiter.
 */
public interface StealService {

    boolean isStealing(@NotNull UUID playerId);

    boolean isCarryingStolenBlock(@NotNull UUID playerId);

    @NotNull Optional<StealSession> getSession(@NotNull UUID thiefId);

    @NotNull Collection<UUID> getActiveStealingPlayers();

    @MainThread
    @NotNull ActionResult startSteal(@NotNull Player thief, @NotNull StoredBlockView target, @NotNull Cause cause);

    @MainThread
    @NotNull ActionResult forceInstantSteal(@NotNull Player thief, @NotNull StoredBlockView target, @NotNull Cause cause);

    @MainThread
    boolean cancelSteal(@NotNull Player thief);

    @NotNull Registration registerRule(@NotNull Plugin owner, @NotNull Key key, int priority, @NotNull StealRule rule);

    default @NotNull Registration registerRule(@NotNull Plugin owner, @NotNull Key key, @NotNull StealRule rule) {
        return registerRule(owner, key, 0, rule);
    }

    @NotNull RuleVerdict evaluateRules(@NotNull StealContext context);
}
