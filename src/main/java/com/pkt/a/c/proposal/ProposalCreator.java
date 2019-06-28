package com.pkt.a.c.proposal;

public class ProposalCreator {
    public static Proposals createProposals(final ProposalSource proposalSource) {
        return new Proposals(proposalSource.createProposal());
    }
}
