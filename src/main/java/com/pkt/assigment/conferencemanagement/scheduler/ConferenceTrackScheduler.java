package com.pkt.assigment.conferencemanagement.scheduler;

import com.pkt.assigment.conferencemanagement.session.ConferenceSession;
import com.pkt.assigment.conferencemanagement.track.ConferenceTrack;

import java.util.ArrayList;
import java.util.List;

public class ConferenceTrackScheduler {
    private List<ConferenceTrack> conferenceTracks = new ArrayList<>();

    public ConferenceTrack createTrack() {
        return new ConferenceTrack();
    }

    public List<ConferenceTrack> getScheduledTracks() {
        return null;
    }

    public List<ConferenceTrack> scheduleTracks(List<ConferenceSession> sessions) {
        return null;
    }
}
