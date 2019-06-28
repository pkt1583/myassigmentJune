package com.pkt.a.c.session;

import com.pkt.a.c.conference.Conference;

import java.time.Duration;
import java.time.LocalTime;


public class EmptySlot extends Session {
    public EmptySlot(final String title, final LocalTime sessionStartTime, final Duration duration) {
        super(title, sessionStartTime, duration);
    }

    @Override
    public Conference.SessionType getSessionType() {
        return Conference.SessionType.EMPTY;
    }
}
