package com.pkt.assigment.conferencemanagement

import com.pkt.assigment.conferencemanagement.track.ConferenceTrack
import groovy.sql.DataSet
import spock.lang.Specification

import java.time.Duration

import static com.pkt.assigment.conferencemanagement.Conference.SessionType.LUNCH
import static org.junit.Assert.assertEquals

class ConferenceTest extends Specification {

    def createConferenceObject() {
        new Conference(DataSet.getProposals())
    }

    void "get total talk duration"() {
        given:
        def conference = createConferenceObject()

        when:
        def totalTalkTime = conference.getTotalTalkDuration()

        then:
        assertEquals(Duration.ofHours(7), totalTalkTime)
    }

    void "Given sessions for 2 days number of conference tracks are 2"() {
        given:
        def conference = createConferenceObject()

        when:
        List<ConferenceTrack> conferenceTracks = conference.getConferenceTracks()

        then:
        assertEquals(2, conferenceTracks.size())
        conferenceTracks.forEach { assertEquals(Duration.ofHours(1), it.conferenceSessions.find { it.sessionType == LUNCH }.duration) }
    }


}
