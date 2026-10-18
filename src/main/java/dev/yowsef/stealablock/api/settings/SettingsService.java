package dev.yowsef.stealablock.api.settings;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Reads and modifies player HUD/notification preferences and personal time modes.
 */
public interface SettingsService {

    enum SettingKey {
        SCOREBOARD,
        EVENT_BAR,
        BLOCK_PRICES,
        INCOME_DISPLAYS,
        STEAL_ALERTS,
        DEPOSIT_ALERTS
    }

    boolean get(@NotNull UUID playerId, @NotNull SettingKey key);

    void set(@NotNull UUID playerId, @NotNull SettingKey key, boolean enabled);

    @NotNull String getTimeMode(@NotNull UUID playerId);

    void setTimeMode(@NotNull UUID playerId, @NotNull String timeModeName);

    void resetAll(@NotNull UUID playerId);
}
