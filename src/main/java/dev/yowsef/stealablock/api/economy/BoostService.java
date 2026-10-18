package dev.yowsef.stealablock.api.economy;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.UUID;

/**
 * Controls temporary personal money boosts and server-wide luck boosts.
 */
public interface BoostService {

    void grantMoneyBoost(@NotNull UUID playerId, @NotNull Duration duration);

    boolean hasMoneyBoost(@NotNull UUID playerId);

    double getMoneyMultiplier(@NotNull UUID playerId);

    void grantServerLuck(@NotNull Duration duration);

    boolean hasServerLuck();

    long getServerLuckExpiryEpochMillis();
}
