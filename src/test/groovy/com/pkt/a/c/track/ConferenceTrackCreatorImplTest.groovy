package com.pkt.a.c.track

import com.pkt.a.c.proposal.Proposals
import com.pkt.a.c.session.DefaultSessionsCreator
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration

import static junit.framework.Assert.assertEquals
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class ConferenceTrackCreatorImplTest {
    @Mock
    private Proposals proposals

    @Mock
    private DefaultSessionsCreator defaultSessionsCreator

    @Test
    void testComputeConferenceTracks() {
        Duration eachDayAvailableDuration = Duration.ofHours(2)
        when(proposals.getNumberOfTracks(eachDayAvailableDuration)).thenReturn(2)
        when(defaultSessionsCreator.createDefaultSessions()).thenReturn(Collections.emptyList())
        def conferenceTrackCreator = new ConferenceTrackCreatorImpl(defaultSessionsCreator)
        List<ConferenceTrack> conferenceTracks = conferenceTrackCreator.computeConferenceTracks(proposals,
                eachDayAvailableDuration)
        assertEquals(2, conferenceTracks.size())
    }

}
