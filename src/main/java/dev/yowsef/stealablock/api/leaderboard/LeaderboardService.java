package dev.yowsef.stealablock.api.leaderboard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

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

    @NotNull List<LeaderboardEntry> getTop(@NotNull LeaderboardType type, int limit);

    void forceRefreshHolograms();
}
