package com.pkt.a.c.track;


import com.pkt.a.c.conference.Conference;
import com.pkt.a.c.session.Session;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ConferenceTrack {
    public final SortedSet<Session> conferenceSessions = new TreeSet<>(Comparator.comparing(Session::getSessionStartTime));
    private final int trackIndex;

    ConferenceTrack(final int trackIndex) {
        this.trackIndex = trackIndex;
    }

    public final <T extends Session> void addSessionsToTrack(final T... inputConferenceSessions) {
        for (final T inputConferenceSession : inputConferenceSessions) {
            conferenceSessions.add(inputConferenceSession);
        }
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    public List<Session> getEmptySlots() {
        return conferenceSessions.stream().filter(session -> session.getSessionType() == Conference.SessionType.EMPTY && !session.getDuration().isZero()).collect(Collectors.toList());
    }
}
