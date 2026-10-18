package dev.yowsef.stealablock.api.modifier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Base context passed into a {@link Modifier} during evaluation.
 */
public interface ModifierContext {

    /**
     * @return the base numerical value before modifiers are applied
     */
    double baseValue();

    /**
     * @return the player subject of the calculation, if applicable
     */
    @Nullable UUID playerId();

    /**
     * Generic context implementation covering income, steal duration, fusion odds,
     * conveyor speed, rarity weights, and progression/shop costs.
     */
    record StandardContext(
            double baseValue,
            @Nullable UUID playerId,
            @Nullable UUID targetPlayerId,
            @Nullable String arenaId,
            int plotNumber,
            @Nullable String subjectId
    ) implements ModifierContext {

        public static @NotNull StandardContext ofPlayer(double baseValue, @NotNull UUID playerId) {
            return new StandardContext(baseValue, playerId, null, null, -1, null);
        }

        public static @NotNull StandardContext ofPlayerSubject(double baseValue, @Nullable UUID playerId, @Nullable String subjectId) {
            return new StandardContext(baseValue, playerId, null, null, -1, subjectId);
        }

        public static @NotNull StandardContext ofSteal(double baseValue, @NotNull UUID thiefId, @NotNull UUID victimId,
                                                       @Nullable String arenaId, int plotNumber, @Nullable String blockId) {
            return new StandardContext(baseValue, thiefId, victimId, arenaId, plotNumber, blockId);
        }

        public @NotNull StandardContext withBase(double newBase) {
            return new StandardContext(newBase, playerId, targetPlayerId, arenaId, plotNumber, subjectId);
        }
    }
}
