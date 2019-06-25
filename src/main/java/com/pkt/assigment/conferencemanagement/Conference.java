package com.pkt.assigment.conferencemanagement;

import com.pkt.assigment.conferencemanagement.session.ConferenceSession;
import com.pkt.assigment.conferencemanagement.track.ConferenceTrack;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static java.time.Duration.between;

public class Conference {
    public final static LocalTime START_TIME = LocalTime.of(9, 0, 0);
    public final static LocalTime END_TIME = LocalTime.of(17, 0, 0);
    public final static LocalTime LUNCH_START_TIME = LocalTime.of(12, 0, 0);
    public final static LocalTime NETWORKING_START_TIME = LocalTime.of(16, 0, 0);
    public final static LocalTime LUNCH_END_TIME = LocalTime.of(13, 0, 0);

    private final List<ConferenceTrack> conferenceTracks;
    private final List<ConferenceSession> proposals;

    public Conference(final List<ConferenceSession> proposals) {
        this.proposals = proposals;
        final int numberOfTracks = getNumberOfTracks(proposals);
        final LinkedList<ConferenceTrack> conferenceTracks = new LinkedList<>();
        for (int i = 0; i < numberOfTracks; i++) {
            conferenceTracks.add(new ConferenceTrack());
        }
        this.conferenceTracks = conferenceTracks;
    }

    public Duration getTotalTalkDuration() {
        return between(START_TIME, END_TIME).minus(between(LUNCH_START_TIME, LUNCH_START_TIME));
    }

    private int getNumberOfTracks(final List<ConferenceSession> proposals) {
        final Duration totalTalkDuration = getTotalTalkDuration();
        final long totalProposedTalkDurationInMinutes = proposals.stream().mapToLong(conferenceSession -> conferenceSession.getDuration().toMinutes()).sum();
        return (int) Math.ceil(totalProposedTalkDurationInMinutes / (totalTalkDuration.toMinutes() * 1.0));
    }

    public List<ConferenceTrack> getConferenceTracks() {
        return conferenceTracks;
    }

    public List<ConferenceSession> getProposals() {
        return proposals;
    }


    public enum SessionType {
        LUNCH, NETWORKING, TALK, EMPTY
    }

}
