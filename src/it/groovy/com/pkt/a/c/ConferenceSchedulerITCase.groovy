package com.pkt.a.c

import com.pkt.a.c.conference.Conference
import com.pkt.a.c.io.input.DurationParser
import com.pkt.a.c.io.output.ConferencePrinter
import com.pkt.a.c.io.output.DefaultConferencePrinter
import com.pkt.a.c.proposal.ProposalCreator
import com.pkt.a.c.proposal.Proposals
import com.pkt.a.c.scheduler.session.SessionSchedulerImpl
import com.pkt.a.c.scheduler.track.ConferenceTrackSchedulerImpl
import com.pkt.a.c.session.DefaultSessionsCreator
import com.pkt.a.c.session.Session
import com.pkt.a.c.track.ConferenceTrackCreatorImpl
import org.junit.Test

import java.time.Duration
import java.time.LocalTime

import static junit.framework.Assert.*

class ConferenceSchedulerITCase {

    @Test
    void testScheduleConferenceSessions() {
        def fileContent = new File(getClass().getClassLoader().getResource("input.txt").toURI()).readLines()
        Proposals proposals = createProposals(fileContent)
        Conference conference = scheduleConference(proposals)
        ConferencePrinter conferencePrinter = new DefaultConferencePrinter()
        StringWriter stringWriter = new StringWriter()
        conferencePrinter.printConferenceSchedule(stringWriter, conference)
        String outputValue = stringWriter.toString()
        assertNotNull(outputValue)
        assertTrue(outputValue.length() != 0)
        conference.conferenceTracks.forEach {
            Session firstSession = it.conferenceSessions.first()
            assertEquals(LocalTime.of(9, 0, 0), firstSession.sessionStartTime)
            Session lastSession = it.conferenceSessions.last()
            if (lastSession.sessionStartTime.isAfter(LocalTime.of(17, 0, 0))) {
                fail("The Networking session schedule should not start after 5 PM")
            }
            if (lastSession.sessionStartTime.isBefore(LocalTime.of(16, 0, 0))) {
                fail("Networking session should not start before 4 PM")
            }
        }
        assertEquals(0, proposals.numberOfProposals())
    }

    Conference scheduleConference(final Proposals proposals) {
        Conference conference = new Conference(proposals, new ConferenceTrackCreatorImpl(new DefaultSessionsCreator() {}))
        conference.scheduleConferenceTalks(new ConferenceTrackSchedulerImpl(new SessionSchedulerImpl()))
        conference
    }

    Proposals createProposals(final List<String> fileContent) {
        ProposalCreator.createProposals({
            fileContent.collect {
                line ->
                    String[] splittedLine = line.split(" ")
                    String durationValue = splittedLine[splittedLine.length - 1]
                    Duration duration = DurationParser.parse(durationValue)
                    return Proposals.createProposal(line.replaceAll(durationValue, ""), duration)
            }
        })
    }
}
