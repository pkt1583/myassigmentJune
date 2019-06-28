package com.pkt.a.c.io.input;

import java.time.Duration;

public class DurationParser {
    public static Duration parse(final String durationValue) {
        if ("lightning".equals(durationValue)) {
            return Duration.ofMinutes(5);
        }
        return Duration.ofMinutes(Long.parseLong(durationValue.replaceAll("min", "")));
    }
}
