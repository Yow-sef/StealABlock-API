package dev.yowsef.stealablock.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Static provider for obtaining the active {@link StealABlockAPI} instance.
 */
public final class StealABlockProvider {

    private static volatile StealABlockAPI instance;

    private StealABlockProvider() {
    }

    /**
     * Returns the active {@link StealABlockAPI} instance.
     *
     * @throws IllegalStateException if StealABlock is not enabled or failed license verification
     */
    public static @NotNull StealABlockAPI get() {
        StealABlockAPI api = instance;
        if (api == null) {
            throw new IllegalStateException("StealABlock API is not loaded (plugin disabled or unlicensed).");
        }
        return api;
    }

    /**
     * @return true if StealABlock API is initialized and ready for use
     */
    public static boolean isAvailable() {
        return instance != null;
    }

    /**
     * @return an {@link java.util.Optional} containing the active {@link StealABlockAPI} if loaded
     */
    public static @NotNull java.util.Optional<StealABlockAPI> getIfAvailable() {
        return java.util.Optional.ofNullable(instance);
    }

    @ApiStatus.Internal
    public static void register(@NotNull StealABlockAPI api) {
        instance = api;
    }

    @ApiStatus.Internal
    public static void unregister() {
        instance = null;
    }
}
