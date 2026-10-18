package dev.yowsef.stealablock.api.modifier;

/**
 * Mathematical operation applied by a {@link Modifier} in the evaluation pipeline.
 * <p>
 * Evaluation order within a pipeline:
 * <ol>
 *   <li>{@link #ADD}: added to the base value</li>
 *   <li>{@link #MULTIPLY}: multiplied sequentially against the running total</li>
 *   <li>{@link #MIN} / {@link #MAX}: lower and upper clamps</li>
 *   <li>{@link #SET}: overrides the final value (highest priority wins)</li>
 * </ol>
 */
public enum Operation {
    ADD,
    MULTIPLY,
    MIN,
    MAX,
    SET
}
