package dev.yowsef.stealablock.api.block;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable snapshot of a registered block definition in {@code blocks.yml}.
 */
public record BlockType(
        @NotNull String id,
        @NotNull String providerName,
        @Nullable String customId,
        @NotNull Material material,
        @NotNull String displayName,
        @NotNull String rarity,
        @NotNull String mutationId,
        double baseIncome,
        double sellValue,
        @Nullable String fusionTargetId,
        @NotNull BlockData blockData,
        @NotNull ItemStack itemSnapshot
) {
    public boolean isCustomBlock() {
        return !"VANILLA".equalsIgnoreCase(providerName) && customId != null && !customId.isEmpty();
    }

    public @NotNull ItemStack createItemStack() {
        return itemSnapshot.clone();
    }
}
