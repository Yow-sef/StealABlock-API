package dev.yowsef.stealablock.api.leaderboard;

import dev.yowsef.stealablock.api.annotation.MainThread;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Queries top players across balance, rebirth, prestige, and credits leaderboards.
 */
public interface LeaderboardService {

    enum LeaderboardType {
        BALANCE,
        REBIRTH,
        PRESTIGE,
        CREDITS
    }

    record LeaderboardEntry(
            int rank,
            @NotNull UUID playerId,
            @Nullable String playerName,
            double value
    ) {}

    /**
     * Synchronous leaderboard query. Safe to call from the main thread; for
     * scoreboard/tab loops prefer {@link #getTopAsync(LeaderboardType, int)}.
     */
    @MainThread
    @NotNull List<LeaderboardEntry> getTop(@NotNull LeaderboardType type, int limit);

    /**
     * Runs the database query off the primary thread. Player names may be
     * resolved from the server user cache.
     */
    default @NotNull CompletableFuture<List<LeaderboardEntry>> getTopAsync(
            @NotNull LeaderboardType type, int limit) {
        return CompletableFuture.supplyAsync(() -> getTop(type, limit));
    }

    @MainThread
    void forceRefreshHolograms();
}
