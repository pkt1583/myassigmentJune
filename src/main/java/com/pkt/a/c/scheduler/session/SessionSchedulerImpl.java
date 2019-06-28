package com.pkt.a.c.scheduler.session;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.session.EmptySlot;
import com.pkt.a.c.session.NetworkingSession;
import com.pkt.a.c.session.Session;
import com.pkt.a.c.session.TalkSession;
import com.pkt.a.c.track.ConferenceTrack;

import java.util.List;

import static com.pkt.a.c.conference.Conference.END_TIME;
import static com.pkt.a.c.conference.Conference.NETWORKING_START_TIME;

public class SessionSchedulerImpl implements SessionScheduler {
    @Override
    public void scheduleSessionsInSlotForTrack(final EmptySlot availableSlot, final List<Proposals.Proposal> proposals, final ConferenceTrack conferenceTrack) {
        proposals.forEach(proposal -> {
            if (availableSlot.getDuration().compareTo(proposal.getDuration()) >= 0) {
                conferenceTrack.conferenceSessions.remove(availableSlot);
                final Session toBeAddedToTrack = createTalkSessionFromAvailableSlot(availableSlot, proposal);
                conferenceTrack.addSessionsToTrack(toBeAddedToTrack);
                if (!availableSlot.getDuration().isZero()) {
                    conferenceTrack.conferenceSessions.add(availableSlot);
                }
             /*   if (!stillSpaceAvailable(availableSlot.getDuration())) {
                    return;
                }*/
            }
        });
    }

    private Session createTalkSessionFromAvailableSlot(final EmptySlot availableSlot, final Proposals.Proposal proposal) {
        final TalkSession talkSession = new TalkSession(proposal.getTitle(), availableSlot.getSessionStartTime(), proposal.getDuration());
        availableSlot.setSessionStartTime(talkSession.getSessionEndTime());
        availableSlot.setDuration(availableSlot.getDuration().minus(talkSession.getDuration()));
        return talkSession;
    }

    //TODO: Check networking cannot be before 4
    @Override
    public void scheduleNetworking(final ConferenceTrack conferenceTrack) {
        final List<Session> nextAvailableSlot = conferenceTrack.getEmptySlots();
        final NetworkingSession networkingSession = new NetworkingSession(END_TIME);
        if (nextAvailableSlot.size() > 1) {
            throw new RuntimeException("Some issue with networking slot");
        } else if ((nextAvailableSlot.size() == 0)) {
            networkingSession.setSessionStartTime(END_TIME);
        } else {
            final EmptySlot availableSlot = (EmptySlot) nextAvailableSlot.get(0);
            conferenceTrack.conferenceSessions.remove(availableSlot);
            if (availableSlot.getSessionStartTime().isAfter(NETWORKING_START_TIME)) {
                networkingSession.setSessionStartTime(availableSlot.getSessionStartTime());
            }
        }
        conferenceTrack.addSessionsToTrack(networkingSession);
    }
}
