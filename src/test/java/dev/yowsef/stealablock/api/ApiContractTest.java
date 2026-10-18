package dev.yowsef.stealablock.api;

import dev.yowsef.stealablock.api.event.CancellableStealABlockEvent;
import dev.yowsef.stealablock.api.event.StealABlockEvent;
import dev.yowsef.stealablock.api.event.conveyor.ConveyorBlockClaimEvent;
import dev.yowsef.stealablock.api.event.conveyor.ConveyorBlockSpawnEvent;
import dev.yowsef.stealablock.api.event.defense.TrapDeployEvent;
import dev.yowsef.stealablock.api.event.economy.BalanceChangeEvent;
import dev.yowsef.stealablock.api.event.economy.CreditsChangeEvent;
import dev.yowsef.stealablock.api.event.fusion.FusionResultEvent;
import dev.yowsef.stealablock.api.event.fusion.FusionStartEvent;
import dev.yowsef.stealablock.api.event.gameevent.GameEventEndEvent;
import dev.yowsef.stealablock.api.event.gameevent.GameEventStartEvent;
import dev.yowsef.stealablock.api.event.lifecycle.StealABlockReadyEvent;
import dev.yowsef.stealablock.api.event.lifecycle.StealABlockReloadEvent;
import dev.yowsef.stealablock.api.event.plot.GateToggleEvent;
import dev.yowsef.stealablock.api.event.plot.PlotAssignEvent;
import dev.yowsef.stealablock.api.event.plot.PlotUnassignEvent;
import dev.yowsef.stealablock.api.event.progression.ContractProgressEvent;
import dev.yowsef.stealablock.api.event.progression.MilestoneClaimEvent;
import dev.yowsef.stealablock.api.event.progression.PlayerPrestigeEvent;
import dev.yowsef.stealablock.api.event.progression.PlayerRebirthEvent;
import dev.yowsef.stealablock.api.event.steal.StealAttemptEvent;
import dev.yowsef.stealablock.api.event.steal.StealCancelEvent;
import dev.yowsef.stealablock.api.event.steal.StealCompleteEvent;
import dev.yowsef.stealablock.api.event.storage.BlockDepositEvent;
import dev.yowsef.stealablock.api.event.storage.BlockSellEvent;
import dev.yowsef.stealablock.api.event.storage.IncomeCollectEvent;
import dev.yowsef.stealablock.api.event.storage.IncomeTickEvent;
import dev.yowsef.stealablock.api.event.storage.MysteryBlockUnboxEvent;
import org.bukkit.event.HandlerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ApiContractTest {

    private static final List<Class<? extends StealABlockEvent>> EVENT_CLASSES = List.of(
            StealABlockReadyEvent.class,
            StealABlockReloadEvent.class,
            PlotAssignEvent.class,
            PlotUnassignEvent.class,
            GateToggleEvent.class,
            ConveyorBlockSpawnEvent.class,
            ConveyorBlockClaimEvent.class,
            BlockDepositEvent.class,
            MysteryBlockUnboxEvent.class,
            IncomeTickEvent.class,
            IncomeCollectEvent.class,
            BlockSellEvent.class,
            StealAttemptEvent.class,
            StealCompleteEvent.class,
            StealCancelEvent.class,
            FusionStartEvent.class,
            FusionResultEvent.class,
            PlayerRebirthEvent.class,
            PlayerPrestigeEvent.class,
            MilestoneClaimEvent.class,
            ContractProgressEvent.class,
            BalanceChangeEvent.class,
            CreditsChangeEvent.class,
            GameEventStartEvent.class,
            GameEventEndEvent.class,
            TrapDeployEvent.class
    );

    @Test
    void testApiVersionSemantics() {
        ApiVersion v130 = new ApiVersion(1, 3, 0);
        assertTrue(v130.isAtLeast(1, 3));
        assertTrue(v130.isAtLeast(1, 2, 9));
        assertFalse(v130.isAtLeast(1, 4));
        assertFalse(v130.isAtLeast(2, 0));
        assertTrue(v130.isCompatibleWith(new ApiVersion(1, 0, 0)));
        assertFalse(v130.isCompatibleWith(new ApiVersion(2, 0, 0)));
    }

    @Test
    void testAllEventsHaveValidBukkitHandlerList() throws Exception {
        for (Class<? extends StealABlockEvent> clazz : EVENT_CLASSES) {
            assertTrue(Modifier.isFinal(clazz.getModifiers()), clazz.getSimpleName() + " should be final");
            Method getHandlerList = clazz.getDeclaredMethod("getHandlerList");
            assertTrue(Modifier.isStatic(getHandlerList.getModifiers()), clazz.getSimpleName() + ".getHandlerList() must be static");
            assertTrue(Modifier.isPublic(getHandlerList.getModifiers()), clazz.getSimpleName() + ".getHandlerList() must be public");
            Object list = getHandlerList.invoke(null);
            assertNotNull(list, clazz.getSimpleName() + ".getHandlerList() returned null");
            assertInstanceOf(HandlerList.class, list);
        }
    }

    @Test
    void testZeroInternalPackageLeaksInApiBytecode() throws IOException {
        Path classesDir = Paths.get("target", "classes");
        assertTrue(Files.exists(classesDir), "target/classes should exist");
        try (Stream<Path> walk = Files.walk(classesDir)) {
            List<Path> classFiles = walk.filter(p -> p.toString().endsWith(".class")).toList();
            assertFalse(classFiles.isEmpty(), "Compiled API classes should not be empty");
            for (Path classFile : classFiles) {
                byte[] bytes = Files.readAllBytes(classFile);
                String raw = new String(bytes, StandardCharsets.ISO_8859_1);
                assertFalse(raw.contains("net/alphastudio/alphastealblock"),
                        "API class leaked internal package: " + classFile);
            }
        }
    }
}
