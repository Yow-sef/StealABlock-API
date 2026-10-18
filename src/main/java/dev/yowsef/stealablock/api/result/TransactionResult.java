package dev.yowsef.stealablock.api.result;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Outcome of a money or credits transaction.
 */
public record TransactionResult(
        @NotNull Status status,
        double previousBalance,
        double newBalance,
        double delta,
        @Nullable String message
) {
    public enum Status {
        SUCCESS,
        INSUFFICIENT_FUNDS,
        INVALID_AMOUNT,
        CANCELLED_BY_EVENT,
        FAILURE
    }

    public boolean success() {
        return status == Status.SUCCESS;
    }

    public static @NotNull TransactionResult ok(double previous, double current, double delta) {
        return new TransactionResult(Status.SUCCESS, previous, current, delta, null);
    }

    public static @NotNull TransactionResult fail(@NotNull Status status, double current, @Nullable String message) {
        return new TransactionResult(status, current, current, 0.0, message);
    }
}
