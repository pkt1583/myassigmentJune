package com.pkt.a.c.io.output;

import com.pkt.a.c.track.ConferenceTrack;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class DefaultConferencePrinter implements ConferencePrinter {

    @Override
    public void printTracks(final PrintWriter pw, final ConferenceTrack conferenceTrack) {
        pw.println();
        pw.println("Track " + conferenceTrack.getTrackIndex() + " : ");
        conferenceTrack.conferenceSessions.forEach(session -> {
            final String sessionStartTime = valueOrNothing(localTime -> localTime.format(DateTimeFormatter.ofPattern("hh:mm a")), session.getSessionStartTime());
            final String durationValue = valueOrNothing(duration -> duration.toMinutes() + "min", session.getDuration());
            pw.println(sessionStartTime + " " + session.getTitle() + " " + durationValue);
        });
        pw.flush();
    }
}
