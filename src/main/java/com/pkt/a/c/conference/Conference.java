package com.pkt.a.c.conference;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.scheduler.exception.ProposalSchedulingException;
import com.pkt.a.c.scheduler.track.ConferenceTrackScheduler;
import com.pkt.a.c.track.ConferenceTrack;
import com.pkt.a.c.track.ConferenceTrackCreator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Conference {
    public final static LocalTime START_TIME = LocalTime.of(9, 0, 0);
    public final static LocalTime END_TIME = LocalTime.of(17, 0, 0);
    public final static LocalTime LUNCH_START_TIME = LocalTime.of(12, 0, 0);
    public final static LocalTime NETWORKING_START_TIME = LocalTime.of(16, 0, 0);
    public final static LocalTime LUNCH_END_TIME = LocalTime.of(13, 0, 0);

    private final List<ConferenceTrack> conferenceTracks;
    private final Proposals proposals;

    public Conference(final Proposals proposals, final ConferenceTrackCreator conferenceTrackCreator) {
        this.proposals = proposals;
        conferenceTracks = conferenceTrackCreator.computeConferenceTracks(proposals, Duration.between(START_TIME, END_TIME
        ).minus(Duration.between(LUNCH_START_TIME, LUNCH_END_TIME)));
    }

    public List<ConferenceTrack> getConferenceTracks() {
        return Collections.unmodifiableList(conferenceTracks);
    }

    public void scheduleConferenceTalks(final ConferenceTrackScheduler conferenceTrackScheduler) {
        conferenceTracks.forEach(conferenceTrack -> {
            final Proposals proposals = this.proposals;
            conferenceTrackScheduler.scheduleProposals(proposals, conferenceTrack);
        });
        if (proposals.numberOfProposals() != 0) {
            throw new ProposalSchedulingException("Could not schedule some proposals " + proposals);
        }
    }

    public enum SessionType {
        LUNCH, NETWORKING, TALK, EMPTY
    }

}
