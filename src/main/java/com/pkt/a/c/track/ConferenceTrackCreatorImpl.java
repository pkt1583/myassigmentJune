package com.pkt.a.c.track;

import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.session.DefaultSessionsCreator;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ConferenceTrackCreatorImpl implements ConferenceTrackCreator {
    private final DefaultSessionsCreator defaultSlotCreator;

    public ConferenceTrackCreatorImpl(final DefaultSessionsCreator defaultSlotCreator) {
        this.defaultSlotCreator = defaultSlotCreator;
    }

    @Override
    public List<ConferenceTrack> computeConferenceTracks(final Proposals proposals, final Duration duration) {
        log.info("Calculating number of Tracks required for {} and duration {} mins ", proposals.numberOfProposals(), duration.toMinutes());
        final int numberOfTracks = proposals.getNumberOfTracks(duration);
        final LinkedList<ConferenceTrack> conferenceTracks = new LinkedList<>();
        for (int i = 0; i < numberOfTracks; i++) {
            final ConferenceTrack conferenceTrack = new ConferenceTrack(i + 1);
            conferenceTrack.conferenceSessions.addAll(defaultSlotCreator.createDefaultSessions());
            conferenceTracks.add(conferenceTrack);
        }
        log.info("Returning {} conference tracks ", conferenceTracks.size());
        return conferenceTracks;
    }


}
