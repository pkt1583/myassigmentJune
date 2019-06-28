package com.pkt.a.c.track;

import com.pkt.a.c.proposal.Proposals;

import java.time.Duration;
import java.util.List;

public interface ConferenceTrackCreator {
    List<ConferenceTrack> computeConferenceTracks(Proposals proposals, Duration duration);

    void populateDefaultSlots(final ConferenceTrack conferenceTrack);
}
