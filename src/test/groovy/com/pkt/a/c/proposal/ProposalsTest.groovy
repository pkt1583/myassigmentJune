package com.pkt.a.c.proposal

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration

import static junit.framework.Assert.assertEquals
import static junit.framework.Assert.assertTrue


@RunWith(MockitoJUnitRunner.class)
class ProposalsTest {
    def proposalTestData = [Proposals.createProposal("firstProposal", Duration.ofMinutes(1))
                            , Proposals.createProposal("secondProposal", Duration.ofMinutes(2))
                            , Proposals.createProposal("thirdProposal", Duration.ofMinutes(3))
                            , Proposals.createProposal("fourthProposal", Duration.ofMinutes(4))
                            , Proposals.createProposal("fifthProposal", Duration.ofMinutes(5))
                            , Proposals.createProposal("sixthProposal", Duration.ofMinutes(5))
                            , Proposals.createProposal("seventhProposal", Duration.ofMinutes(5))
                            , Proposals.createProposal("eightProposal", Duration.ofMinutes(4))
                            , Proposals.createProposal("ninthProposal", Duration.ofMinutes(1))
                            , Proposals.createProposal("tenthProposal", Duration.ofMinutes(8))
    ]

    @Test
    void testCreateProposal() {
        Proposals.Proposal proposal = Proposals.createProposal("title", Duration.ofMinutes(22))
        assertEquals(Duration.ofMinutes(22), proposal.duration)
        assertEquals("title", proposal.title)
    }

    @Test
    void testGetProposalsForTheSlot() {
        Proposals proposals = new Proposals(proposalTestData.collect())
        List<Proposals.Proposal> proposalList = proposals.getProposalsForTheSlot(Duration.ofMinutes(4))
        assertEquals(1, proposalList.size())
        proposals = new Proposals(proposalTestData.collect())
        proposalList = proposals.getProposalsForTheSlot(Duration.ofMinutes(15))
        assertEquals(3, proposalList.size())
        assertEquals(Duration.ofMinutes(8), proposalList.get(0).duration)
        def modifiedTestData = proposalTestData.collect().findAll { (it.duration >= Duration.ofMinutes(2)) }
        proposals = new Proposals(modifiedTestData)
        proposalList = proposals.getProposalsForTheSlot(Duration.ofMinutes(1))
        assertTrue(proposalList.size() == 0)
    }

    @Test
    void testGetNumberOfTracks() {
        Proposals proposals = new Proposals(proposalTestData.collect())
        assertEquals(4, proposals.getNumberOfTracks(Duration.ofMinutes(10)))
    }
}
