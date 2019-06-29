package com.pkt.a.c.scheduler.session

import com.pkt.a.c.conference.Conference
import com.pkt.a.c.proposal.Proposals
import com.pkt.a.c.scheduler.session.exception.NetworkingSchedulingException
import com.pkt.a.c.session.EmptySlot
import com.pkt.a.c.session.NetworkingSession
import com.pkt.a.c.track.ConferenceTrack
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration
import java.time.LocalTime

import static junit.framework.Assert.assertEquals
import static junit.framework.Assert.assertNull

@RunWith(MockitoJUnitRunner.class)
class SessionSchedulerImplTest {
    def proposalTestData = [Proposals.createProposal("firstProposal", Duration.ofMinutes(30))
                            , Proposals.createProposal("secondProposal", Duration.ofMinutes(45))
                            , Proposals.createProposal("thirdProposal", Duration.ofMinutes(45))
                            , Proposals.createProposal("fourthProposal", Duration.ofMinutes(30))
                            , Proposals.createProposal("fifthProposal", Duration.ofMinutes(15))
                            , Proposals.createProposal("sixthProposal", Duration.ofMinutes(15))
    ]

    @Test
    void testScheduleSessionsInSlotForTrack() {
        def sessionScheduler = new SessionSchedulerImpl()
        EmptySlot emptySlot = new EmptySlot(LocalTime.of(9, 0, 0), Duration.ofHours(4))
        def proposal = proposalTestData.collect()
        def conferenceTrack = new ConferenceTrack(1)
        sessionScheduler.scheduleSessionsInSlotForTrack(emptySlot, proposal, conferenceTrack)
        assertEquals(7, conferenceTrack.conferenceSessions.size())
        assertEquals(Duration.ofHours(1), conferenceTrack.conferenceSessions[6].duration)

    }

    @Test
    void testScheduleSessionsInSlotForTrackWithMoreProposals() {
        def sessionScheduler = new SessionSchedulerImpl()
        EmptySlot emptySlot = new EmptySlot(LocalTime.of(9, 0, 0), Duration.ofHours(4))
        def proposal = proposalTestData.collect()
        proposal.add(Proposals.createProposal("BigProposal", Duration.ofHours(2)))
        def conferenceTrack = new ConferenceTrack(1)
        sessionScheduler.scheduleSessionsInSlotForTrack(emptySlot, proposal, conferenceTrack)
        assertNull(conferenceTrack.conferenceSessions.find { it.title.equals("BigProposal") })

    }

    @Test
    void testScheduleNetworking() {
        def sessionScheduler = new SessionSchedulerImpl()
        ConferenceTrack conferenceTrack = new ConferenceTrack(1)
        conferenceTrack.conferenceSessions.add(new EmptySlot(LocalTime.of(16, 0, 0), Duration.ofHours(1)))
        sessionScheduler.scheduleNetworking(conferenceTrack)
        NetworkingSession networkingSession = conferenceTrack.conferenceSessions.find { it.sessionType == Conference.SessionType.NETWORKING }
        assertEquals(LocalTime.of(16, 0, 0), networkingSession.sessionStartTime)
    }

    @Test
    void testScheduleNetworkingBeforeTime() {
        def sessionScheduler = new SessionSchedulerImpl()
        ConferenceTrack conferenceTrack = new ConferenceTrack(1)
        conferenceTrack.conferenceSessions.add(new EmptySlot(LocalTime.of(13, 0, 0), Duration.ofHours(1)))
        sessionScheduler.scheduleNetworking(conferenceTrack)
        NetworkingSession networkingSession = conferenceTrack.conferenceSessions.find { it.sessionType == Conference.SessionType.NETWORKING }
        assertEquals(LocalTime.of(17, 0, 0), networkingSession.sessionStartTime)
    }

    @Test(expected = NetworkingSchedulingException.class)
    void testScheduleNetworkingAfterTime() {
        def sessionScheduler = new SessionSchedulerImpl()
        ConferenceTrack conferenceTrack = new ConferenceTrack(1)
        conferenceTrack.conferenceSessions.add(new EmptySlot(LocalTime.of(18, 0, 0), Duration.ofHours(1)))
        sessionScheduler.scheduleNetworking(conferenceTrack)
        NetworkingSession networkingSession = conferenceTrack.conferenceSessions.find { it.sessionType == Conference.SessionType.NETWORKING }
    }

}
