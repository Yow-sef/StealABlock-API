# StealABlock Developer API

Official public developer API for the StealABlock Minecraft plugin (Paper 1.21+).

## Dependency Setup

### Gradle (Groovy)

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
    maven { url 'https://repo.papermc.io/repository/maven-public/' }
}

dependencies {
    compileOnly 'com.github.Yow-sef:StealABlock-API:1.3.0'
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
    compileOnly("com.github.Yow-sef:StealABlock-API:1.3.0")
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

<dependencies>
    <dependency>
        <groupId>com.github.Yow-sef</groupId>
        <artifactId>StealABlock-API</artifactId>
        <version>1.3.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Add to plugin.yml

```yaml
depend: [StealABlock]
# or:
# softdepend: [StealABlock]
```

## Quick Start

### 1. Access the API

```java
import dev.yowsef.stealablock.api.StealABlockAPI;
import dev.yowsef.stealablock.api.StealABlockProvider;

public class MyAddon extends JavaPlugin {
    @Override
    public void onEnable() {
        if (!StealABlockProvider.isAvailable()) {
            getLogger().warning("StealABlock is not available!");
            return;
        }

        StealABlockAPI api = StealABlockProvider.get();
        getLogger().info("Connected to StealABlock API v" + api.getVersion());
    }
}
```

### 2. Register an Income / Steal Modifier

```java
api.modifiers().register(ModifierTarget.INCOME, (player, base) -> {
    if (player.hasPermission("myaddon.vip")) {
        return base.multiply(1.5);
    }
    return base;
});
```

### 3. Register a Steal Rule

```java
api.stealing().registerRule((thief, victim, targetBlock, item) -> {
    if (victim.hasPermission("myaddon.shield")) {
        return StealDecision.deny("Target player has active VIP shield protection.");
    }
    return StealDecision.allow();
});
```

### 4. Listen to Events

```java
@EventHandler
public void onConveyorClaim(ConveyorBlockClaimEvent event) {
    event.getPlayer().sendMessage("Claimed " + event.getBlockType().name());
}

@EventHandler
public void onSteal(StealCompleteEvent event) {
    getServer().broadcastMessage(event.getThief().getName() + " stole from " + event.getVictim().getName());
}
```

## Services

- `api.economy()`: player balances, transactions, credit transactions.
- `api.arenas()`: arenas, plots, game states, conveyor endpoints.
- `api.storage()`: player anvils, stored blocks, income rates.
- `api.stealing()`: active steals, steal rules, channel times.
- `api.modifiers()`: pipeline for income, channel duration, deposit duration, and sell prices.
- `api.mutations()`: custom mutation tiers, drop rates, and fusion rules.
- `api.events()`: trigger custom server game events.
- `api.contracts()`: player contracts, completions, resets.
- `api.ui()`: menu registrations and views.
- `api.placeholders()`: addon custom placeholders.
