package io.github.vicen621.loriath.utils;

import io.github.vicen621.loriath.LoriathMod;

public class StringUtils {

    public static void log(String s) {
        LoriathMod.LOGGER.info(s);
    }

    public static void debug(String s) {
        if (LoriathMod.DEBUG)
            log(s);
    }
}
