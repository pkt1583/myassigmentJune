package com.pkt.assigment.conferencemanagement.scheduler;

import com.pkt.assigment.conferencemanagement.Conference;
import com.pkt.assigment.conferencemanagement.session.ConferenceSession;
import com.pkt.assigment.conferencemanagement.track.ConferenceTrack;

import java.util.Iterator;
import java.util.List;

public class ConferenceScheduler {
    private final Conference conference;

    public ConferenceScheduler(final Conference conference) {
        this.conference = conference;
    }

    public List<ConferenceTrack> getConferenceSchedule() {
        return conference.getConferenceTracks();
    }

    public void scheduleConferenceTalks() {
        conference.getConferenceTracks().forEach(conferenceTrack -> {
            final Iterator<ConferenceSession> proposals = conference.getProposals().iterator();
            while (proposals.hasNext()) {
                final ConferenceSession scheduledSession = conferenceTrack.trySchedule(proposals.next());
                if (scheduledSession != null) {
                    proposals.remove();
                }
            }
            conferenceTrack.addNetworkingSessionToTrack();
        });
    }
}
