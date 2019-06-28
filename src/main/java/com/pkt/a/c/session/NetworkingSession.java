package com.pkt.a.c.session;


import com.pkt.a.c.conference.Conference;

import java.time.LocalTime;

import static com.pkt.a.c.conference.Conference.SessionType.NETWORKING;


public class NetworkingSession extends Session {
    public NetworkingSession(final LocalTime sessionStartTime) {
        //TODO: Validate time
        super("Networking Event", sessionStartTime, null);
    }

    @Override
    public Conference.SessionType getSessionType() {
        return NETWORKING;
    }
}
