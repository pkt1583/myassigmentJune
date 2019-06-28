package com.pkt.a.c.io.output;

import com.pkt.a.c.conference.Conference;
import com.pkt.a.c.track.ConferenceTrack;

import java.io.PrintWriter;
import java.util.function.Function;

public interface ConferencePrinter {
    default void printConferenceSchedule(final PrintWriter pw, final Conference conference) {
        conference.getConferenceTracks().forEach(conferenceTrack -> printTracks(pw, conferenceTrack));
    }

    void printTracks(PrintWriter pw, ConferenceTrack conferenceTrack);

    default <T> String valueOrNothing(final Function<T, String> printFunction, final T object) {
        if (object == null) {
            return "";
        }
        return printFunction.apply(object);
    }
}
