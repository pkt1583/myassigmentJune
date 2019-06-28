package com.pkt.a.c.scheduler.session;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.session.EmptySlot;
import com.pkt.a.c.track.ConferenceTrack;

import java.util.List;

public interface SessionScheduler {
    void scheduleSessionsInSlotForTrack(EmptySlot availableSlot, List<Proposals.Proposal> proposals, ConferenceTrack conferenceTrack);

    void scheduleNetworking(ConferenceTrack conferenceTrack);
}
