package dev.yowsef.stealablock.example;

import dev.yowsef.stealablock.api.StealABlockAPI;
import dev.yowsef.stealablock.api.StealABlockProvider;
import dev.yowsef.stealablock.api.addon.StealABlockAddon;
import dev.yowsef.stealablock.api.block.Mutation;
import dev.yowsef.stealablock.api.cause.Cause;
import dev.yowsef.stealablock.api.event.progression.PlayerRebirthEvent;
import dev.yowsef.stealablock.api.event.steal.StealCompleteEvent;
import dev.yowsef.stealablock.api.gameevent.GameEventType;
import dev.yowsef.stealablock.api.modifier.Modifier;
import dev.yowsef.stealablock.api.modifier.ModifierContext.StandardContext;
import dev.yowsef.stealablock.api.modifier.ModifierType;
import dev.yowsef.stealablock.api.modifier.Operation;
import dev.yowsef.stealablock.api.steal.RuleVerdict;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Reference addon showcasing the StealABlock v1.3.2 Developer API:
 * <ul>
 *   <li>{@link Modifier} pipeline: 1.25x VIP income boost with hologram tag</li>
 *   <li>{@link dev.yowsef.stealablock.api.steal.StealRule}: Newbie protection for Rebirth 0 victims</li>
 *   <li>{@link GameEventType}: Custom "Gold Rush" server event</li>
 *   <li>{@link Mutation}: Custom "Cosmic" 20x mutation tier</li>
 *   <li>Custom PlaceholderAPI sub-key: {@code %stealablock_example_status%}</li>
 *   <li>Typed Bukkit events: {@link StealCompleteEvent} and {@link PlayerRebirthEvent}</li>
 * </ul>
 */
public final class ExampleStealAddon extends JavaPlugin implements StealABlockAddon, Listener {

    @Override
    public void onEnable() {
        StealABlockProvider.getIfAvailable().ifPresentOrElse(
                api -> api.addons().register(this, this),
                () -> getLogger().warning("StealABlock API is not available!")
        );
    }

    @Override
    public void onApiReady(@NotNull StealABlockAPI api) {
        Bukkit.getPluginManager().registerEvents(this, this);

        // 1. Register a 1.25x VIP Income Modifier with hologram tag
        api.modifiers().register(this, ModifierType.INCOME, new Modifier<>() {
            private final Key key = Key.key("exampleaddon", "vip_income");

            @Override
            public @NotNull Key key() {
                return key;
            }

            @Override
            public int priority() {
                return 100;
            }

            @Override
            public @NotNull Operation operation() {
                return Operation.MULTIPLY;
            }

            @Override
            public double evaluate(@NotNull StandardContext context) {
                if (context.playerId() == null) return 1.0;
                Player player = Bukkit.getPlayer(context.playerId());
                return (player != null && player.hasPermission("stealablock.vip")) ? 1.25 : 1.0;
            }

            @Override
            public @Nullable String displayTag(@NotNull StandardContext context) {
                return "&#55FFFF[VIP 1.25x]";
            }
        });

        // 2. Register a Newbie Protection StealRule (Rebirth >= 5 cannot steal from Rebirth 0)
        api.steals().registerRule(this, Key.key("exampleaddon", "newbie_shield"), 10, ctx -> {
            int thiefRebirth = api.rebirth().getRebirthLevel(ctx.thief().getUniqueId());
            int victimRebirth = api.rebirth().getRebirthLevel(ctx.victimId());
            if (thiefRebirth >= 5 && victimRebirth == 0) {
                return RuleVerdict.deny("&#FF5555Newbie Shield: You cannot steal from Rebirth 0 players!");
            }
            return RuleVerdict.allow();
        });

        // 3. Register a Custom Server Event ("Gold Rush")
        Key goldRushKey = Key.key("exampleaddon", "gold_rush");
        api.gameEvents().registerEventType(this, GameEventType.simple(
                goldRushKey,
                "&#FFD700Gold Rush",
                "&#FFAA00Golden blocks earn bonus cash!",
                Material.GOLD_BLOCK,
                180
        ));

        // 4. Register a Custom Mutation ("Cosmic" 20x tier)
        api.mutations().register(this, new Mutation(
                "cosmic",
                "cosmic_",
                "Cosmic",
                "&#AA00AA",
                20.0,
                0.01,
                4,
                null
        ));

        // 5. Register a Custom PlaceholderAPI sub-key (%stealablock_example_status%)
        api.placeholders().register(this, "example_status", (player, params) -> {
            if (player == null) return "Offline";
            int rebirth = api.rebirth().getRebirthLevel(player.getUniqueId());
            return rebirth >= 5 ? "Veteran" : "Rookie";
        });

        getLogger().info("Registered ExampleStealAddon hooks with StealABlock API v" + api.getApiVersion());
    }

    @EventHandler
    public void onStealComplete(StealCompleteEvent event) {
        StealABlockProvider.getIfAvailable().ifPresent(api -> {
            // Award a small $250 heist bonus on every completed steal
            api.money().deposit(
                    event.getThief().getUniqueId(),
                    250.0,
                    Cause.plugin(this, "heist_bonus")
            );
        });
    }

    @EventHandler
    public void onPlayerRebirth(PlayerRebirthEvent event) {
        getLogger().info(event.getPlayer().getName() + " rebirthed to level " + event.getNewLevel());
    }
}
