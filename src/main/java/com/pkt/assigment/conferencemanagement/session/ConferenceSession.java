package com.pkt.assigment.conferencemanagement.session;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class ConferenceSession {
    private final String title;
    private final Duration duration;

}
