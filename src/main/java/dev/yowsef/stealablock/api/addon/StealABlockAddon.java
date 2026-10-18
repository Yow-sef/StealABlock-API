package dev.yowsef.stealablock.api.addon;

import dev.yowsef.stealablock.api.StealABlockAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Optional lifecycle interface that third-party plugins may implement on their main
 * {@code JavaPlugin} class (or register via {@link AddonRegistry}) to receive automatic
 * callbacks when StealABlock initializes or reloads.
 */
public interface StealABlockAddon {

    /**
     * Called once StealABlock is fully loaded and ready to accept registrations.
     *
     * @param api the active StealABlock API instance
     */
    void onApiReady(@NotNull StealABlockAPI api);

    /**
     * Called whenever StealABlock configurations are reloaded via {@code /sab reload}.
     *
     * @param api the active StealABlock API instance
     */
    default void onStealABlockReload(@NotNull StealABlockAPI api) {
    }
}
