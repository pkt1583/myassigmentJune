/*
package com.pkt.assigment.conferencemanagement.track;

import com.google.common.collect.Maps;
import com.pkt.assigment.conferencemanagement.session.ConferenceSession;
import org.junit.Test;

import java.time.Duration;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ConferenceTrackTest {


    @Test
    public void createSession() {
    }

    @Test
    public void addSessionToTrack() {
    }

    @Test
    public void findSlotForSession() {
        final ConferenceTrack conferenceTrack = new ConferenceTrack();
        final ConferenceSession conferenceSession = new ConferenceSession("Writing Fast Tests Against Enterprise " +
                "Rails", Duration.ofMinutes(60L));
        boolean scheduled = conferenceTrack.trySchedule(conferenceSession);
        assertTrue(scheduled);
    }

    @Test
    public void givenAlreadySessionScheduleThenGetSlotForASession() {
        ConferenceTrack conferenceTrack = new ConferenceTrack();
        conferenceTrack.addSessionsToTrack(ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Writing Fast Tests Against Enterprise Rails").build());
    }
}*/
