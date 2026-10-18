package dev.yowsef.stealablock.api.plot;

import dev.yowsef.stealablock.api.annotation.MainThread;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Controls base security gates and virtual barrier protections.
 */
public interface GateService {

    boolean isGateOpen(@NotNull UUID ownerId, int gateId);

    @MainThread
    void openGate(@NotNull Player owner, @NotNull PlotView plot, int gateId);

    @MainThread
    void closeGate(@NotNull Player owner, @NotNull PlotView plot, int gateId);

    @MainThread
    void toggleGate(@NotNull Player owner, @NotNull PlotView plot, int gateId);

    @MainThread
    void lockThiefGate(@NotNull PlotView plot);

    @MainThread
    void unlockThiefGate(@NotNull PlotView plot);

    @NotNull List<Location> getGateBlocks(@NotNull PlotView plot, int gateId);
}
