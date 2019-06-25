package com.pkt.assigment.conferencemanagement.session;

import com.pkt.assigment.conferencemanagement.Conference.SessionType;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;

@Data
@Builder
public class ConferenceSession {
    private LocalTime sessionStartTime;
    private final LocalTime sessionEndTime;
    private String title;
    private Duration duration;
    private SessionType sessionType;

    public LocalTime getSessionEndTime() {
        if (duration != null) {
            return sessionStartTime.plus(duration);
        } else {
            return LocalTime.MAX;
        }
    }

}
