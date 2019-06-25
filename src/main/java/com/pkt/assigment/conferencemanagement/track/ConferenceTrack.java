package com.pkt.assigment.conferencemanagement.track;

import com.pkt.assigment.conferencemanagement.session.ConferenceSession;

import java.time.Duration;
import java.util.*;

import static com.pkt.assigment.conferencemanagement.Conference.*;
import static com.pkt.assigment.conferencemanagement.Conference.SessionType.*;

public class ConferenceTrack {
    private final SortedSet<ConferenceSession> conferenceSessions = new TreeSet<>(Comparator.comparing(ConferenceSession::getSessionStartTime));

    public ConferenceTrack() {
        addSessionToTrack(ConferenceSession.builder().sessionStartTime(START_TIME).duration(Duration.between(START_TIME, LUNCH_START_TIME)).sessionType(EMPTY).build());
        addSessionToTrack(ConferenceSession.builder().sessionStartTime(LUNCH_START_TIME).duration(Duration.ofHours(1)).sessionType(LUNCH).build());
        addSessionToTrack(ConferenceSession.builder().sessionStartTime(LUNCH_END_TIME).sessionType(EMPTY).duration(Duration.between(LUNCH_END_TIME, END_TIME)).build());
    }

    public void addNetworkingSessionToTrack() {
        final Iterator<ConferenceSession> nextAvailableSlot = findNextEmptyDuration();
        final ConferenceSession networkingSession = ConferenceSession.builder().sessionType(NETWORKING).sessionStartTime(END_TIME).build();
        while (nextAvailableSlot.hasNext()) {
            final ConferenceSession avaibleSlot = nextAvailableSlot.next();
            if (avaibleSlot.getSessionStartTime().isAfter(NETWORKING_START_TIME)) {
                networkingSession.setSessionStartTime(avaibleSlot.getSessionStartTime());
            }
        }
        addSessionToTrack(networkingSession);
    }

    public Set<ConferenceSession> getConferenceSessions() {
        return conferenceSessions;
    }

    private void addSessionToTrack(final ConferenceSession... conferenceSession) {
        conferenceSessions.addAll(Arrays.asList(conferenceSession));
    }

    public ConferenceSession trySchedule(final ConferenceSession conferenceSession) {
        final Iterator<ConferenceSession> nextAvailableDuration = findNextEmptyDuration();
        while (nextAvailableDuration.hasNext()) {
            final ConferenceSession availableSlot = nextAvailableDuration.next();
            if (availableSlot.getDuration().compareTo(conferenceSession.getDuration()) >= 0) {
                divideAndFit(availableSlot, conferenceSession);
                return conferenceSession;
            }
        }
        return null;
    }

    private Iterator<ConferenceSession> findNextEmptyDuration() {
        return conferenceSessions.stream().filter(conferenceSession -> conferenceSession.getSessionType() == EMPTY).iterator();
    }

    private void divideAndFit(final ConferenceSession availableSlot, final ConferenceSession conferenceSession) {
        conferenceSessions.remove(availableSlot);
        conferenceSession.setSessionStartTime(availableSlot.getSessionStartTime());
        availableSlot.setSessionStartTime(conferenceSession.getSessionEndTime());
        availableSlot.setDuration(availableSlot.getDuration().minus(conferenceSession.getDuration()));
        conferenceSession.setSessionType(TALK);
        conferenceSessions.add(conferenceSession);
        if (!availableSlot.getSessionStartTime().equals(END_TIME)) {
            conferenceSessions.add(availableSlot);
        }
    }


}
