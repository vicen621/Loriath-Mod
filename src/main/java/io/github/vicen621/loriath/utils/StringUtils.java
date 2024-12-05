package io.github.vicen621.loriath.utils;

import io.github.vicen621.loriath.Loriath;

public class StringUtils {

    public static void log(String s) {
        Loriath.LOGGER.info(s);
    }

    public static void debug(String s) {
        if (Loriath.DEBUG)
            log(s);
    }
}
