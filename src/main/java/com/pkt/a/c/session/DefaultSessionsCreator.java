package com.pkt.a.c.session;

import com.pkt.a.c.conference.Conference;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.pkt.a.c.conference.Conference.*;

public interface DefaultSessionsCreator {
    default List<Session> createDefaultSessions() {
        final List<Session> conferenceTracks = new ArrayList<>();
        //Can't we autopopulate Empty slots based on Luch time slot
        conferenceTracks.add(new EmptySlot(Conference.START_TIME, Duration.between(Conference.START_TIME, LUNCH_START_TIME)));
        conferenceTracks.add(new LunchSession());
        conferenceTracks.add(new EmptySlot(LUNCH_END_TIME, Duration.between(LUNCH_END_TIME, END_TIME)));
        return conferenceTracks;
    }

}
