package dev.yowsef.stealablock.api.provider;

import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Pluggable custom block provider allowing third-party plugins (e.g., Oraxen, CraftEngine, custom engines)
 * to supply custom block visuals and items to StealABlock.
 */
public interface CustomBlockProvider {

    /**
     * @return provider identifier used in {@code blocks.yml} (e.g. {@code "ORAXEN"})
     */
    @NotNull String providerId();

    boolean isAvailable();

    boolean isValidBlock(@NotNull String customId);

    @Nullable BlockData resolveBlockData(@NotNull String customId);

    @Nullable ItemStack resolveItemStack(@NotNull String customId);
}
