package com.pkt.a.c.scheduler.session;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.scheduler.session.exception.NetworkingSchedulingException;
import com.pkt.a.c.session.EmptySlot;
import com.pkt.a.c.session.NetworkingSession;
import com.pkt.a.c.session.Session;
import com.pkt.a.c.session.TalkSession;
import com.pkt.a.c.track.ConferenceTrack;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.List;

import static com.pkt.a.c.conference.Conference.END_TIME;
import static com.pkt.a.c.conference.Conference.NETWORKING_START_TIME;

@Slf4j
public class SessionSchedulerImpl implements SessionScheduler {
    @Override
    public void scheduleSessionsInSlotForTrack(final EmptySlot availableSlot, final List<Proposals.Proposal> proposals, final ConferenceTrack conferenceTrack) {
        final int proposalSize = proposals.size();
        log.info("Trying to Fit {} proposals in {} mins of available slot for conference track {} ", proposalSize, availableSlot.getDuration().toMinutes(), conferenceTrack.getTrackIndex());
        proposals.forEach(proposal -> {
            if (availableSlot.getDuration().compareTo(proposal.getDuration()) >= 0) {
                conferenceTrack.conferenceSessions.remove(availableSlot);
                final Session toBeAddedToTrack = createTalkSessionFromAvailableSlot(availableSlot, proposal);
                conferenceTrack.addSessionsToTrack(toBeAddedToTrack);
                if (!availableSlot.getDuration().isZero()) {
                    conferenceTrack.conferenceSessions.add(availableSlot);
                }
            }
        });
        log.info("Completed Fitting process. Fitted  {} eligible proposals", proposalSize);
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
            log.error("More than one slot found to configure networking. The available slots are {} ", nextAvailableSlot.size());
            throw new NetworkingSchedulingException("Some issue with networking slot");
        } else if ((nextAvailableSlot.size() == 0)) {
            networkingSession.setSessionStartTime(END_TIME);
        } else {
            final EmptySlot availableSlot = (EmptySlot) nextAvailableSlot.get(0);
            if (availableSlot.getSessionStartTime().isAfter(LocalTime.of(17, 0, 0))) {
                log.error("Issues with algorithm. The session has exceeded 5 PM deadline");
                throw new NetworkingSchedulingException("Available Slot exceeded max Networking time");
            }
            conferenceTrack.conferenceSessions.remove(availableSlot);
            if (availableSlot.getSessionStartTime().compareTo(NETWORKING_START_TIME) >= 0) {
                networkingSession.setSessionStartTime(availableSlot.getSessionStartTime());
            }
        }
        conferenceTrack.addSessionsToTrack(networkingSession);
    }
}
