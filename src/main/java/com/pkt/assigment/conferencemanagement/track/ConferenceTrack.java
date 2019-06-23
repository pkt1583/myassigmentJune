package com.pkt.assigment.conferencemanagement.track;

import com.pkt.assigment.conferencemanagement.session.ConferenceSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConferenceTrack {
    private final LocalTime startTime = LocalTime.of(9, 0, 0);
    private final LocalTime lunchStartTime = LocalTime.of(12, 0, 0);
    private final LocalTime lunchEndTime = LocalTime.of(13, 0, 0);
    private final LocalTime endTime = LocalTime.of(17, 0, 0);

    private final List<ConferenceSession> conferenceSessions = new ArrayList<>();


    public void addSessionsToTrack(final ConferenceSession... inputConferenceSessions) {
        conferenceSessions.addAll(Arrays.asList(inputConferenceSessions));
    }

}
