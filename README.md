# StealABlock Developer API

Public developer API for the StealABlock Minecraft plugin (Paper 1.21+, Java 21).
The full guide is in [`docs/API.md`](docs/API.md).

## Dependency setup

Replace the JitPack coordinates if you publish from a fork or a separate
API-only repository.

### Gradle (Groovy)

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
    maven { url 'https://repo.papermc.io/repository/maven-public/' }
}

dependencies {
    compileOnly 'com.github.Yow-sef:StealABlock-API:v1.3.2'
    compileOnly 'io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT'
}
```

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("com.github.Yow-sef:StealABlock-API:v1.3.2")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    <repository>
        <id>papermc</id>
        <url>https://repo.papermc.io/repository/maven-public/</url>
    </repository>
</repositories>

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
```

### plugin.yml

```yaml
depend: [stealablock]
```

## Quick start

```java
import dev.yowsef.stealablock.api.StealABlockAPI;
import dev.yowsef.stealablock.api.StealABlockProvider;
import dev.yowsef.stealablock.api.addon.StealABlockAddon;

public final class MyAddon extends JavaPlugin implements StealABlockAddon {

    @Override
    public void onEnable() {
        StealABlockProvider.get().addons().register(this, this);
    }

    @Override
    public void onApiReady(StealABlockAPI api) {
        getLogger().info("Connected to StealABlock API v" + api.getApiVersion());
        // Register modifiers, steal rules, mutations, placeholders, etc.
    }
}
```

## Main services

| Accessor | Purpose |
|---|---|
| `api.players()` | Player snapshots, arena/Bedrock checks, tutorial state |
| `api.money()` / `api.credits()` | Atomic balance and credit transactions |
| `api.blocks()` / `api.mutations()` | Block definitions and mutation tiers |
| `api.storage()` | Stored blocks, sales, collection, upgrades |
| `api.steals()` | Active steals and pluggable steal rules |
| `api.plots()` / `api.gates()` / `api.arenas()` | Plot, gate, and arena state |
| `api.conveyors()` | Conveyor control and moving blocks |
| `api.fusion()` | Fusion eligibility and real odds |
| `api.rebirth()` / `api.prestige()` | Progression state and actions |
| `api.milestones()` / `api.contracts()` | Discoveries, claims, heist board |
| `api.gameEvents()` | Built-in and custom server events |
| `api.gear()` / `api.cosmetics()` | Gear catalog/abilities and cosmetics |
| `api.settings()` | Player HUD/notification settings |
| `api.leaderboards()` | Sync and async leaderboard queries |
| `api.menus()` | Java and Bedrock menu opener |
| `api.modifiers()` / `api.placeholders()` | Extension pipelines and placeholders |

See [`docs/API.md`](docs/API.md) for event lists, modifier types, threading
rules, and extension registry details.

