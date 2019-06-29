package com.pkt.a.c.io.output;

import com.pkt.a.c.session.Session;
import com.pkt.a.c.track.ConferenceTrack;

import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;

public class DefaultConferencePrinter implements ConferencePrinter {

    @Override
    public void printTracks(final Writer pw, final ConferenceTrack conferenceTrack) throws IOException {
        pw.write("Track " + conferenceTrack.getTrackIndex() + " : ");
        pw.write(System.lineSeparator());
        for (final Session session : conferenceTrack.conferenceSessions) {
            final String sessionStartTime = valueOrNothing(localTime -> localTime.format(DateTimeFormatter.ofPattern("hh:mm a")), session.getSessionStartTime());
            final String durationValue = valueOrNothing(duration -> duration.toMinutes() + "min", session.getDuration());
            pw.write(sessionStartTime + " " + session.getTitle() + " " + durationValue);
            pw.write(System.lineSeparator());
        }
        pw.flush();
    }
}
