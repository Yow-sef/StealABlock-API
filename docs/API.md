# StealABlock Developer API (v1.3.2)

Java 21 API for StealABlock (`dev.yowsef.stealablock.api`). It exposes the
plugin's gameplay systems to external plugins without reflection or internal
package access.

The API module has no dependency on the core module. Core bundles the API
classes in its shaded jar, so addons compile against `stealablock-api` and run
against the installed StealABlock plugin.

---

## 1. Dependency setup

### Maven

Add the Paper repository and both dependencies. `paper-api` is required because
the API exposes Bukkit/Paper types; `provided` dependencies are not transitive.

```xml
<repositories>
    <repository>
        <id>papermc</id>
        <url>https://repo.papermc.io/repository/maven-public/</url>
    </repository>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Yow-sef</groupId>
        <artifactId>StealABlock-API</artifactId>
        <version>v1.3.2</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>io.papermc.paper</groupId>
        <artifactId>paper-api</artifactId>
        <version>1.21.4-R0.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

If you publish from a fork, replace the JitPack coordinates. The official
artifact is published from `Yow-sef/StealABlock-API`. Local builds use
`dev.yowsef:stealablock-api:1.3.2` after `mvn install`.

### plugin.yml

```yaml
depend:
  - stealablock
```

Use the exact lowercase plugin name. Paper's dependency resolver is case-sensitive.

---

## 2. Obtaining the API

```java
import dev.yowsef.stealablock.api.StealABlockAPI;
import dev.yowsef.stealablock.api.StealABlockProvider;

StealABlockAPI api = StealABlockProvider.get();          // throws if not ready
StealABlockProvider.getIfAvailable().ifPresent(sab -> {}); // safe lookup

// Or via Bukkit:
StealABlockAPI service = Bukkit.getServicesManager().load(StealABlockAPI.class);
```

Always check `api.getApiVersion().isAtLeast(1, 3)` before using newer features.
`getApiVersion()` describes the API contract; `getPluginVersion()` describes the
installed plugin jar.

---

## 3. Addon lifecycle

Implement `StealABlockAddon` and register in `onEnable`:

```java
public final class MyAddon extends JavaPlugin implements StealABlockAddon {

    @Override
    public void onEnable() {
        StealABlockProvider.get().addons().register(this, this);
    }

    @Override
    public void onApiReady(@NotNull StealABlockAPI api) {
        // Register modifiers, rules, events, mutations, placeholders...
    }
}
```

`onApiReady` is invoked once per addon, immediately if the API is already
ready. Addons enabled after StealABlock (the normal `depend` case) and addons
enabled later via `/reload` are both supported. `onStealABlockReload` is called
after `/sab reload`.

Every registration returns a `Registration` handle. Registrations are also
removed automatically when the owning plugin disables.

---

## 4. Modifier pipeline (`ModifierService`)

Each pipeline runs in four passes:

1. `ADD` — added to the base value
2. `MULTIPLY` — multiplied against the running total
3. `MIN` / `MAX` — clamps
4. `SET` — final override; the last (highest priority) SET wins

`priority()` orders modifiers **within the same operation pass**, not across
passes. Lower runs earlier.

### Wired pipeline types

| Type | Base value | Where it is applied |
|---|---|---|
| `INCOME` | 1.0 multiplier | Anvil income per second |
| `SELL_PRICE` | Block sell value | Stored-block sales |
| `STEAL_DURATION` | Seconds | Steal countdown (clamped to at least 1s) |
| `FUSION_SUCCESS` | 0.0–1.0 chance | Real fusion roll and `FusionOdds` |
| `CONVEYOR_SPEED` | 1.0 multiplier | Conveyor movement |
| `RARITY_WEIGHT` | Base spawn weight | Normal conveyor spawn selection |
| `REBIRTH_COST` | Money | Rebirth eligibility, GUI, Bedrock form, PAPI |
| `PRESTIGE_COST` | Money | Prestige eligibility, event payload, GUI, Bedrock form |
| `GEAR_PRICE` | Money | Special gear-shop price display and purchase |

Modifier return values must be finite. Non-finite results are ignored and
logged; `INCOME`, `SELL_PRICE`, and costs are additionally clamped at zero.

### Example

```java
api.modifiers().register(this, ModifierType.INCOME, new Modifier<>() {
    @Override public @NotNull Key key() { return Key.key("myaddon", "guild_boost"); }
    @Override public int priority() { return 100; }
    @Override public @NotNull Operation operation() { return Operation.MULTIPLY; }

    @Override
    public double evaluate(@NotNull StandardContext ctx) {
        return hasGuildPerk(ctx.playerId()) ? 1.5 : 1.0;
    }

    @Override
    public @Nullable String displayTag(@NotNull StandardContext ctx) {
        return "&#55FF55[1.5x GUILD]";
    }
});
```

`Breakdown` exposes `baseValue()`, `finalValue()`, every applied `Step`, and
`activeDisplayTags()`. `StandardContext` carries the player, optional target,
arena, plot number, and subject id (block id / gear id / level).

---

## 5. Steal rules (`StealService`)

Rules run in priority order before the countdown starts. The first denial
stops evaluation.

```java
api.steals().registerRule(this, Key.key("myaddon", "pvp_truce"), 10, ctx -> {
    if (areAllies(ctx.thief().getUniqueId(), ctx.victimId())) {
        return RuleVerdict.deny("&#FF5555You cannot steal from a clan ally!");
    }
    return RuleVerdict.allow();
});
```

Rules are also used by the API's `startSteal`; `forceInstantSteal` intentionally
bypasses them.

---

## 6. Domain services

| Accessor | Interface | Notes |
|---|---|---|
| `api.players()` | `PlayerService` | Profiles, arena checks, Bedrock, tutorial state |
| `api.money()` | `MoneyService` | Atomic deposit/withdraw/set with `Cause` |
| `api.credits()` | `CreditsService` | Atomic credit transactions |
| `api.boosts()` | `BoostService` | Personal money boost and server luck |
| `api.blocks()` | `BlockRegistry` | Block definitions, weighted rolls, item snapshots |
| `api.mutations()` | `MutationRegistry` | Built-in + addon mutation tiers |
| `api.storage()` | `StorageService` | Anvil blocks, sales, collection, upgrades |
| `api.steals()` | `StealService` | Active sessions, rules, start/cancel |
| `api.plots()` | `PlotService` | Plot lookup, assignment, location checks |
| `api.gates()` | `GateService` | Gate state and open/close/lock |
| `api.arenas()` | `ArenaService` | Arena queries and validation |
| `api.conveyors()` | `ConveyorService` | Conveyor control and moving blocks |
| `api.fusion()` | `FusionService` | Eligibility, odds, start |
| `api.rebirth()` | `RebirthService` | Levels, costs, multipliers, execution |
| `api.prestige()` | `PrestigeService` | Requirements and execution |
| `api.milestones()` | `MilestoneService` | Discoveries, claims, custom reward handlers |
| `api.contracts()` | `ContractService` | Heist board state, progress, rerolls |
| `api.gameEvents()` | `GameEventService` | Built-in + custom server events |
| `api.gear()` | `GearService` | Catalog, grants, custom ability hooks |
| `api.cosmetics()` | `CosmeticService` | Armor trims and name colors |
| `api.settings()` | `SettingsService` | HUD/notification preferences |
| `api.leaderboards()` | `LeaderboardService` | Sync `getTop` and async `getTopAsync` |
| `api.menus()` | `MenuService` | Opens Java or Bedrock menus |
| `api.placeholders()` | `PlaceholderRegistry` | `%stealablock_<key>%` sub-placeholders |

`money()` and `credits()` are the supported way to change balances. Core
gameplay, Vault calls, and API calls all fire `BalanceChangeEvent` /
`CreditsChangeEvent` when listeners are registered.

---

## 7. Events

All events extend `StealABlockEvent` and carry a `Cause` (type, source plugin,
detail).

- Lifecycle: `StealABlockReadyEvent`, `StealABlockReloadEvent`
- Economy: `BalanceChangeEvent`, `CreditsChangeEvent`
- Storage: `BlockDepositEvent`, `BlockSellEvent`, `IncomeTickEvent`,
  `IncomeCollectEvent`, `MysteryBlockUnboxEvent`
- Conveyor: `ConveyorBlockSpawnEvent`, `ConveyorBlockClaimEvent`
- Steal: `StealAttemptEvent`, `StealCompleteEvent`, `StealCancelEvent`
- Fusion: `FusionStartEvent`, `FusionResultEvent`
- Progression: `PlayerRebirthEvent`, `PlayerPrestigeEvent`,
  `MilestoneClaimEvent`, `ContractProgressEvent`
- Plot/defense: `PlotAssignEvent`, `PlotUnassignEvent`, `GateToggleEvent`,
  `TrapDeployEvent`
- Server events: `GameEventStartEvent`, `GameEventEndEvent`

Numeric financial event setters (`BlockSellEvent`, `IncomeTickEvent`,
`IncomeCollectEvent`, `BalanceChangeEvent`) reject non-finite/negative values
with `IllegalArgumentException`. Duration setters clamp to at least one second.
Exceptions thrown by listeners are isolated by Bukkit, but addon code should
not throw.

---

## 8. Extension registries

### Custom block providers

```java
api.registerBlockProvider(this, new CustomBlockProvider() {
    @Override public @NotNull String providerId() { return "oraxen"; }
    @Override public boolean isAvailable() { return true; }
    @Override public boolean isValidBlock(@NotNull String customId) { return ...; }
    @Override public @Nullable BlockData resolveBlockData(@NotNull String customId) { ... }
    @Override public @Nullable ItemStack resolveItemStack(@NotNull String customId) { ... }
});
```

Registering a provider triggers a block reload. In `blocks.yml` use
`provider: oraxen` plus `custom-id: <id>`. Built-in `VANILLA`, `ITEMSADDER`, and
`NEXO` providers remain available.

### Custom mutations

`MutationRegistry.register` adds a tier used by `detect`, `BlockRegistry.base`,
`BlockRegistry.mutated`, and fusion chains. Core's built-in mystery-unbox and
spawn-weight rules still use the normal/golden/diamond/rainbow definitions; a
custom tier needs matching block entries in `blocks.yml` (for example
`cosmic_diamond_block`) to be spawnable.

Built-in mutation ids (`normal`, `golden`, `diamond`, `rainbow`) and the
`stealablock`/`minecraft` key namespaces are reserved.

### Custom game events

`registerEventType` registers a `GameEventType`. `startEvent`/`stopActiveEvent`
drive `onStart`/`onTick`/`onStop`, fire `GameEventStartEvent`/`GameEventEndEvent`,
and report accurate remaining time. Custom and built-in events are mutually
exclusive.

### Gear abilities

`registerAbility` registers global hooks: `onUse` fires on right-click and
`onAttack` fires when the player damages another player. Abilities are not tied
to a specific gear id; check ownership in your handler if needed.

### Milestone reward handlers

Register a handler with a namespaced key:

```java
api.milestones().registerRewardHandler(this, Key.key("myaddon", "cosmetic_crate"),
        (player, milestoneId, argument) -> { ... });
```

In `milestones.yml`, set `reward-handler: myaddon:cosmetic_crate` and optionally
`reward-argument: some-value`.

### Placeholders

```java
api.placeholders().register(this, "myaddon_status", (player, params) -> {
    return player == null ? "Offline" : "Online";
});
```

This exposes `%stealablock_myaddon_status%`. Built-in placeholder prefixes are
reserved.

### Custom contract types

Custom contract types are not part of the v1.3 API. Use the built-in heist
board types (`STEAL`, `EARN`, `DEPOSIT`, `FUSE`) and `recordProgress` for those.
Registering arbitrary contract types is intentionally unsupported until the
core board stores non-enum contract definitions.

---

## 9. Threading and errors

- Methods annotated `@MainThread` must run on the primary server thread and
  throw `IllegalStateException` otherwise.
- `loadProfileAsync` schedules its snapshot on the primary thread and returns a
  `CompletableFuture`; it is safe to call from any thread.
- Read-only snapshot records (`PlayerProfile`, `PlotView`, `StoredBlockView`,
  `ArenaView`, `BlockType`, etc.) are defensive copies and can be read from any
  thread.
- Registry registration is thread-safe, but registrations should normally
  happen on the main thread during `onEnable`/`onApiReady`.
- Addon exceptions in modifiers, rules, placeholders, gear abilities, milestone
  handlers, and game-event callbacks are caught and logged. A broken modifier
  cannot take down income calculation.

---

## 10. Vault and `/sab api`

When Vault is installed and `vault.provide-economy: true` is set, StealABlock
registers `VaultEconomyHook`. Vault balance changes use the same atomic
economy path and fire `BalanceChangeEvent`.

`/sab api` prints the API version, plugin version, Vault bridge state, active
addons, modifier/rule counts, mutation/event counts, and placeholder count.
