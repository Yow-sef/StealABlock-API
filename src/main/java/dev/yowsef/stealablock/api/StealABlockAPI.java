package dev.yowsef.stealablock.api;

import dev.yowsef.stealablock.api.addon.AddonRegistry;
import dev.yowsef.stealablock.api.arena.ArenaService;
import dev.yowsef.stealablock.api.block.BlockRegistry;
import dev.yowsef.stealablock.api.block.MutationRegistry;
import dev.yowsef.stealablock.api.conveyor.ConveyorService;
import dev.yowsef.stealablock.api.cosmetic.CosmeticService;
import dev.yowsef.stealablock.api.economy.BoostService;
import dev.yowsef.stealablock.api.economy.CreditsService;
import dev.yowsef.stealablock.api.economy.MoneyService;
import dev.yowsef.stealablock.api.fusion.FusionService;
import dev.yowsef.stealablock.api.gameevent.GameEventService;
import dev.yowsef.stealablock.api.gear.GearService;
import dev.yowsef.stealablock.api.leaderboard.LeaderboardService;
import dev.yowsef.stealablock.api.modifier.ModifierService;
import dev.yowsef.stealablock.api.placeholder.PlaceholderRegistry;
import dev.yowsef.stealablock.api.player.PlayerService;
import dev.yowsef.stealablock.api.plot.GateService;
import dev.yowsef.stealablock.api.plot.PlotService;
import dev.yowsef.stealablock.api.progression.ContractService;
import dev.yowsef.stealablock.api.progression.MilestoneService;
import dev.yowsef.stealablock.api.progression.PrestigeService;
import dev.yowsef.stealablock.api.progression.RebirthService;
import dev.yowsef.stealablock.api.provider.CustomBlockProvider;
import dev.yowsef.stealablock.api.settings.SettingsService;
import dev.yowsef.stealablock.api.steal.StealService;
import dev.yowsef.stealablock.api.storage.StorageService;
import dev.yowsef.stealablock.api.ui.MenuService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Root entry point for the StealABlock Developer API.
 * <p>
 * Obtain an instance via {@link StealABlockProvider#get()} or Bukkit's {@code ServicesManager}:
 * <pre>{@code
 * StealABlockAPI api = StealABlockProvider.get();
 * }</pre>
 */
public interface StealABlockAPI {

    @NotNull ApiVersion getApiVersion();

    @NotNull String getPluginVersion();

    // Domain Services
    @NotNull PlayerService players();

    @NotNull MoneyService money();

    @NotNull CreditsService credits();

    @NotNull BoostService boosts();

    @NotNull BlockRegistry blocks();

    @NotNull StorageService storage();

    @NotNull StealService steals();

    @NotNull PlotService plots();

    @NotNull GateService gates();

    @NotNull ArenaService arenas();

    @NotNull ConveyorService conveyors();

    @NotNull FusionService fusion();

    @NotNull RebirthService rebirth();

    @NotNull PrestigeService prestige();

    @NotNull MilestoneService milestones();

    @NotNull ContractService contracts();

    @NotNull GameEventService gameEvents();

    @NotNull GearService gear();

    @NotNull CosmeticService cosmetics();

    @NotNull SettingsService settings();

    @NotNull LeaderboardService leaderboards();

    @NotNull MenuService menus();

    // Extension Registries & Pipelines
    @NotNull ModifierService modifiers();

    @NotNull MutationRegistry mutations();

    @NotNull PlaceholderRegistry placeholders();

    @NotNull AddonRegistry addons();

    @NotNull Registration registerBlockProvider(@NotNull Plugin owner, @NotNull CustomBlockProvider provider);
}
