package dev.yowsef.stealablock.api.economy;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.TransactionResult;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Manages player heist credits with {@link Cause} tracking.
 */
public interface CreditsService {

    int getCredits(@NotNull UUID playerId);

    boolean has(@NotNull UUID playerId, int amount);

    @NotNull TransactionResult deposit(@NotNull UUID playerId, int amount, @NotNull Cause cause);

    @NotNull TransactionResult withdraw(@NotNull UUID playerId, int amount, @NotNull Cause cause);

    @NotNull TransactionResult setCredits(@NotNull UUID playerId, int amount, @NotNull Cause cause);
}
