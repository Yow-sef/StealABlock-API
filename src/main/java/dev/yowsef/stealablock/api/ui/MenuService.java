package dev.yowsef.stealablock.api.ui;

import dev.yowsef.stealablock.api.annotation.MainThread;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Platform-aware menu service that opens Floodgate Bedrock Forms for Bedrock players
 * and standard Chest GUIs for Java players automatically.
 */
public interface MenuService {

    enum MenuType {
        PERSONAL_MENU,
        INDEX,
        FUSION,
        REBIRTH,
        PRESTIGE,
        MILESTONES,
        CONTRACTS,
        GEAR_SHOP,
        COSMETICS,
        SETTINGS,
        MAP_SELECTOR,
        EVENTS
    }

    @MainThread
    boolean open(@NotNull Player player, @NotNull MenuType menuType);
}
