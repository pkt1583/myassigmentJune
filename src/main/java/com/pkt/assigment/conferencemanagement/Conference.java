package com.pkt.assigment.conferencemanagement;

import com.pkt.assigment.conferencemanagement.scheduler.ConferenceTrackScheduler;
import com.pkt.assigment.conferencemanagement.session.ConferenceSession;
import com.pkt.assigment.conferencemanagement.track.ConferenceTrack;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    private ConferenceTrackScheduler scheduler = new ConferenceTrackScheduler();

    public List<ConferenceTrack> scheduleTracks(List<ConferenceSession> sessions) {
        return scheduler.scheduleTracks(sessions);
    }

    public List<ConferenceTrack> getScheduledTracks() {
        return scheduler.getScheduledTracks();
    }

}
