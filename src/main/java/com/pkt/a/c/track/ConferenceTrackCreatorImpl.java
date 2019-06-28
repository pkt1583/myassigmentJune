package com.pkt.a.c.track;

import com.pkt.a.c.conference.Conference;
import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.session.EmptySlot;
import com.pkt.a.c.session.LunchSession;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.pkt.a.c.conference.Conference.*;

public class ConferenceTrackCreatorImpl implements ConferenceTrackCreator {
    @Override
    public List<ConferenceTrack> computeConferenceTracks(final Proposals proposals, final Duration duration) {

        final int numberOfTracks = proposals.getNumberOfTracks(duration);
        final LinkedList<ConferenceTrack> conferenceTracks = new LinkedList<>();
        for (int i = 0; i < numberOfTracks; i++) {
            final ConferenceTrack conferenceTrack = new ConferenceTrack(i + 1);
            populateDefaultSlots(conferenceTrack);
            conferenceTracks.add(conferenceTrack);
        }
        return conferenceTracks;
    }

    @Override
    public void populateDefaultSlots(final ConferenceTrack conferenceTrack) {
        //Can't we autopopulate Empty slots based on Luch time slot
        conferenceTrack.addSessionsToTrack(new EmptySlot("", Conference.START_TIME, Duration.between(Conference.START_TIME, LUNCH_START_TIME)));
        conferenceTrack.addSessionsToTrack(new LunchSession());
        conferenceTrack.addSessionsToTrack(new EmptySlot("", LUNCH_END_TIME, Duration.between(LUNCH_END_TIME, END_TIME)));
    }
}
