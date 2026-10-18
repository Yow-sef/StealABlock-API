package dev.yowsef.stealablock.api.player;

import dev.yowsef.stealablock.api.annotation.AnyThread;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Provides unified player snapshots, Bedrock checks, and tutorial state.
 */
public interface PlayerService {

    @NotNull PlayerProfile getProfile(@NotNull UUID playerId);

    @AnyThread
    @NotNull CompletableFuture<PlayerProfile> loadProfileAsync(@NotNull UUID playerId);

    boolean isInArena(@NotNull UUID playerId);

    boolean isBedrockPlayer(@NotNull UUID playerId);

    boolean hasSeenTutorial(@NotNull UUID playerId);

    void resetTutorial(@NotNull UUID playerId);
}
