package com.pkt.a.c.scheduler.track;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.track.ConferenceTrack;

public interface ConferenceTrackScheduler {
    void scheduleProposals(Proposals proposals, ConferenceTrack conferenceTrack);
}
