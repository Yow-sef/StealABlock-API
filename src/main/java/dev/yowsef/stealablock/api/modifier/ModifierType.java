package dev.yowsef.stealablock.api.modifier;

import dev.yowsef.stealablock.api.modifier.ModifierContext.StandardContext;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Typed identifier for a calculation pipeline in StealABlock.
 *
 * @param <C> context type passed to modifiers of this type
 */
public final class ModifierType<C extends ModifierContext> {

    private static final Map<String, ModifierType<?>> VALUES = new LinkedHashMap<>();

    /** Per-second block income multiplier pipeline (base = 1.0 multiplier or block base income). */
    public static final ModifierType<StandardContext> INCOME = register("income");

    /** Seconds required for a thief to complete a steal (base = event steal duration, min 1s). */
    public static final ModifierType<StandardContext> STEAL_DURATION = register("steal_duration");

    /** Sell value when a player sells a stored block. */
    public static final ModifierType<StandardContext> SELL_PRICE = register("sell_price");

    /** Success chance [0.0 .. 1.0] when fusing blocks. */
    public static final ModifierType<StandardContext> FUSION_SUCCESS = register("fusion_success");

    /** Conveyor movement speed multiplier. */
    public static final ModifierType<StandardContext> CONVEYOR_SPEED = register("conveyor_speed");

    /** Rarity weight multiplier when spawning blocks on the conveyor. */
    public static final ModifierType<StandardContext> RARITY_WEIGHT = register("rarity_weight");

    /** Money cost required for a player's next rebirth. */
    public static final ModifierType<StandardContext> REBIRTH_COST = register("rebirth_cost");

    /** Money cost required for a player's next prestige. */
    public static final ModifierType<StandardContext> PRESTIGE_COST = register("prestige_cost");

    /** Money price of a gear item in the Gear Shop. */
    public static final ModifierType<StandardContext> GEAR_PRICE = register("gear_price");

    private final String id;

    private ModifierType(@NotNull String id) {
        this.id = id;
    }

    private static <C extends ModifierContext> @NotNull ModifierType<C> register(@NotNull String id) {
        ModifierType<C> type = new ModifierType<>(id);
        VALUES.put(id, type);
        return type;
    }

    public @NotNull String id() {
        return id;
    }

    public static @NotNull Collection<ModifierType<?>> values() {
        return Collections.unmodifiableCollection(VALUES.values());
    }

    @Override
    public String toString() {
        return "ModifierType[" + id + "]";
    }
}
