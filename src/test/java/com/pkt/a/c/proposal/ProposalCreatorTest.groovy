package com.pkt.a.c.proposal

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.Duration

import static junit.framework.Assert.assertEquals
import static junit.framework.Assert.assertTrue
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class ProposalCreatorTest {
    @Mock
    ProposalSource proposalSource

    @Test
    void testCreateProposals() {
        when(proposalSource.createProposal()).thenReturn([Proposals.createProposal("testProposal", Duration.ofMinutes(10))])
        Proposals proposals = ProposalCreator.createProposals(proposalSource)
        assertEquals(1, proposals.numberOfProposals())
        List<Proposals.Proposal> proposalList = proposals.proposals
        assertTrue(proposalList.size() == 1)
        proposalList.forEach { assertTrue(it.title == "testProposal"); assertTrue(it.duration == Duration.ofMinutes(10)) }
    }
}
