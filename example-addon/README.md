# StealABlock Example Addon

Reference addon for the public StealABlock Developer API. It demonstrates:

- Addon registration through `StealABlockAddon`
- A VIP income modifier with a hologram `displayTag`
- A newbie-protection steal rule
- A custom game event
- A custom mutation tier
- A custom PlaceholderAPI sub-placeholder
- Listening to `StealCompleteEvent` and `PlayerRebirthEvent`

## Build

```bash
mvn clean package
```

The output is:

```text
target/stealablock-example-addon-1.3.2.jar
```

Drop it into `plugins/` on a server that has `stealablock-1.3.2.jar` and restart.

The addon declares:

```yaml
depend:
  - stealablock
```

Use the exact lowercase plugin name. Paper's dependency resolver is case-sensitive.

## Published API dependency

The addon compiles against:

```xml
<groupId>com.github.Yow-sef</groupId>
<artifactId>StealABlock-API</artifactId>
<version>v1.3.2</version>
<scope>provided</scope>
```

See [`../docs/API.md`](../docs/API.md) for the full API reference.
