package dev.yowsef.stealablock.api.result;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Outcome of a gameplay or storage action invoked via the API.
 */
public record ActionResult(
        @NotNull Status status,
        @Nullable String reason
) {
    public enum Status {
        SUCCESS,
        DENIED_BY_RULE,
        CANCELLED_BY_EVENT,
        REQUIREMENTS_NOT_MET,
        STORAGE_FULL,
        NOT_IN_PLOT,
        STALE_REFERENCE,
        NOT_FOUND,
        FAILURE
    }

    public static final ActionResult SUCCESS = new ActionResult(Status.SUCCESS, null);

    public boolean success() {
        return status == Status.SUCCESS;
    }

    public static @NotNull ActionResult ok() {
        return SUCCESS;
    }

    public static @NotNull ActionResult fail(@NotNull Status status, @Nullable String reason) {
        return new ActionResult(status, reason);
    }
}
