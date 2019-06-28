package com.pkt.a.c.session;

import com.pkt.a.c.conference.Conference;

import java.time.Duration;
import java.time.LocalTime;

public abstract class Session {
    private final String title;

    public String getTitle() {
        return title;
    }


    public Duration getDuration() {
        return duration;
    }

    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(final LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    private LocalTime sessionEndTime;
    private Duration duration;
    private LocalTime sessionStartTime;

    Session(final String title, final LocalTime sessionStartTime, final Duration duration) {
        this.title = title;
        this.duration = duration;
        this.sessionStartTime = sessionStartTime;
    }

    public abstract Conference.SessionType getSessionType();

    public LocalTime getSessionEndTime() {
        if (duration != null && sessionStartTime != null) {
            return sessionStartTime.plus(duration);
        } else {
            return LocalTime.MAX;
        }
    }


}
