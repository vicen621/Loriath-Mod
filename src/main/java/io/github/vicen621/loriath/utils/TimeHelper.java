package io.github.vicen621.loriath.utils;

import io.github.vicen621.loriath.common.events.TickEvents;

public class TimeHelper {
    private static long serverCounter = 1L;

    public TimeHelper() {
        TickEvents.ServerTickCallback.EVENT.register(() -> ++serverCounter);
    }

    public static boolean hasServerTicksPassed(int tickDelay) {
        return serverCounter % (long) tickDelay == 0L;
    }
}
