package com.pkt.a.c.scheduler.track

import com.pkt.a.c.proposal.Proposals
import com.pkt.a.c.scheduler.session.SessionScheduler
import com.pkt.a.c.session.EmptySlot
import com.pkt.a.c.track.ConferenceTrack
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration
import java.time.LocalTime

import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner.class)
class ConferenceTrackSchedulerImplTest {
    def proposalTestData = [
            Proposals.createProposal("secondProposal", Duration.ofMinutes(45))
            , Proposals.createProposal("thirdProposal", Duration.ofMinutes(45))
            , Proposals.createProposal("firstProposal", Duration.ofMinutes(30))
    ]
    @Mock
    private SessionScheduler sessionScheduler;

    @Test
    void testScheduleProposals() {
        def conferenceTrackScheduler = new ConferenceTrackSchedulerImpl(sessionScheduler)
        ConferenceTrack conferenceTrack = new ConferenceTrack(1)
        EmptySlot emptySlot = new EmptySlot(LocalTime.of(12, 0, 0), Duration.ofHours(2))
        conferenceTrack.conferenceSessions.add(emptySlot)
        def testProposals = proposalTestData.collect()
        def proposals = new Proposals(testProposals)
        conferenceTrackScheduler.scheduleProposals(proposals, conferenceTrack)

        verify(sessionScheduler, times(1)).scheduleSessionsInSlotForTrack(emptySlot, testProposals, conferenceTrack)

        verify(sessionScheduler, times(1)).scheduleNetworking(conferenceTrack)
    }
}
