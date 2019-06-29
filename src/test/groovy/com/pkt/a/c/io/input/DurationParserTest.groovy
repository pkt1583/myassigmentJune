package com.pkt.a.c.io.input


import org.junit.Test

import java.time.Duration

import static junit.framework.Assert.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class DurationParserTest {
    @Test
    void testParse() {
        assertEquals(Duration.ofMinutes(30), DurationParser.parse("30min"))
        assertThrows(NumberFormatException.class, { DurationParser.parse("30m") })
        assertEquals(Duration.ofMinutes(5), DurationParser.parse("lightning"))
    }
}
