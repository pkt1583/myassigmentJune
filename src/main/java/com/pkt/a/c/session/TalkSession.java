package com.pkt.a.c.session;

import com.pkt.a.c.conference.Conference;

import java.time.Duration;
import java.time.LocalTime;

public class TalkSession extends Session {
    public TalkSession(final String title, final LocalTime startTime, final Duration duration) {
        super(title, startTime, duration);
    }

    @Override
    public Conference.SessionType getSessionType() {
        return Conference.SessionType.TALK;
    }

}
