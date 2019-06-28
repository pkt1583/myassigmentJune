package com.pkt.a.c.session;


import com.pkt.a.c.conference.Conference;

import java.time.LocalTime;

import static com.pkt.a.c.conference.Conference.SessionType.LUNCH;

public class LunchSession extends Session {
    public LunchSession() {
        super("Lunch", LocalTime.NOON, null);
    }

    @Override
    public Conference.SessionType getSessionType() {
        return LUNCH;
    }
}
