package com.pkt.a.c.conference

import com.pkt.a.c.proposal.Proposals
import com.pkt.a.c.scheduler.track.ConferenceTrackScheduler
import com.pkt.a.c.track.ConferenceTrack
import com.pkt.a.c.track.ConferenceTrackCreator
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration

import static org.mockito.Mockito.*
import static org.testng.Assert.assertThrows

@RunWith(MockitoJUnitRunner.class)
class ConferenceTest extends GroovyTestCase {
    @Mock
    private ConferenceTrackCreator conferenceTrackCreator

    private Proposals proposals = new Proposals(Collections.emptyList())

    @Test
    void testGetConferenceTracks() {
        when(conferenceTrackCreator.computeConferenceTracks(proposals, Duration.ofHours(7))).thenReturn([new ConferenceTrack(0), new ConferenceTrack(1), new ConferenceTrack(2)])
        def conference = new Conference(proposals, conferenceTrackCreator)
        List<ConferenceTrack> conferenceTracks = conference.getConferenceTracks()
        assertEquals(3, conferenceTracks.size())
        assertThrows(UnsupportedOperationException.class, { conferenceTracks.remove(0) })
    }

   
    @Mock
    ConferenceTrackScheduler conferenceTrackScheduler

    @Test
    void testScheduleConferenceTalks() {
        ConferenceTrack conferenceTrack0 = new ConferenceTrack(0)
        ConferenceTrack conferenceTrack1 = new ConferenceTrack(1)
        ConferenceTrack conferenceTrack2 = new ConferenceTrack(2)
        when(conferenceTrackCreator.computeConferenceTracks(proposals, Duration.ofHours(7))).thenReturn([conferenceTrack0, conferenceTrack1, conferenceTrack2])
        def conference = new Conference(proposals, conferenceTrackCreator)
        conference.scheduleConferenceTalks(conferenceTrackScheduler)
        verify(conferenceTrackScheduler, times(1)).scheduleProposals(proposals, conferenceTrack0)
        verify(conferenceTrackScheduler, times(1)).scheduleProposals(proposals, conferenceTrack1)
        verify(conferenceTrackScheduler, times(1)).scheduleProposals(proposals, conferenceTrack2)
    }
}
