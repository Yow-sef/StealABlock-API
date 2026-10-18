package dev.yowsef.stealablock.api.economy;

import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.result.TransactionResult;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Manages player money balances with full {@link Cause} tracking and Vault integration status.
 */
public interface MoneyService {

    double getStartingBalance();

    double getBalance(@NotNull UUID playerId);

    boolean has(@NotNull UUID playerId, double amount);

    @NotNull TransactionResult deposit(@NotNull UUID playerId, double amount, @NotNull Cause cause);

    @NotNull TransactionResult withdraw(@NotNull UUID playerId, double amount, @NotNull Cause cause);

    @NotNull TransactionResult setBalance(@NotNull UUID playerId, double amount, @NotNull Cause cause);

    boolean isVaultBridgeActive();
}
