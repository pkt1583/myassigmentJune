package com.pkt.a.c.io.output;

import com.pkt.a.c.conference.Conference;
import com.pkt.a.c.track.ConferenceTrack;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public interface ConferencePrinter {
    default void printConferenceSchedule(final Writer pw, final Conference conference) {
        conference.getConferenceTracks().forEach(conferenceTrack -> {
            try {
                printTracks(pw, conferenceTrack);
            } catch (final IOException e) {
                throw new RuntimeException("Issue with sending data to output", e);
            }
        });
    }

    void printTracks(Writer pw, ConferenceTrack conferenceTrack) throws IOException;

    default <T> String valueOrNothing(final Function<T, String> printFunction, final T object) {
        if (object == null) {
            return "";
        }
        return printFunction.apply(object);
    }
}
