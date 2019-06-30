package com.pkt.a.c.scheduler.track;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.scheduler.session.SessionScheduler;
import com.pkt.a.c.session.EmptySlot;
import com.pkt.a.c.session.Session;
import com.pkt.a.c.track.ConferenceTrack;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ConferenceTrackSchedulerImpl implements ConferenceTrackScheduler {
    private final SessionScheduler sessionScheduler;

    public ConferenceTrackSchedulerImpl(final SessionScheduler sessionScheduler) {
        this.sessionScheduler = sessionScheduler;
    }

    @Override
    public void scheduleProposals(final Proposals proposals, final ConferenceTrack conferenceTrack) {
        log.info("Scheduling proposals to conference track {} ", conferenceTrack.getTrackIndex());
        final List<Session> nextAvailableDuraion = conferenceTrack.getEmptySlots();
        nextAvailableDuraion.forEach(session -> {
            final EmptySlot availableSlot = (EmptySlot) session;
            final List<Proposals.Proposal> proposalList = proposals.getProposalsForTheSlot(availableSlot.getDuration());
            sessionScheduler.scheduleSessionsInSlotForTrack(availableSlot, proposalList, conferenceTrack);
        });
        sessionScheduler.scheduleNetworking(conferenceTrack);
        log.info("Completed scheduling proposals to conference track {} ", conferenceTrack.getTrackIndex());
    }
}
