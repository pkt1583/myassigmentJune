package com.pkt.assigment.conferencemanagement

import groovy.sql.DataSet
import spock.lang.Specification

import java.time.Duration

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
        int numberOfTracks = conference.getConferenceTracks()

        then:
        assertEquals(2, numberOfTracks)
    }
    

}
