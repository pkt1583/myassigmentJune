package com.pkt.a.c.proposal;

import java.time.Duration;
import java.util.*;

public class Proposals {
    private final List<Proposal> proposals;
    private final TreeMap<Duration, List<Proposal>> durationProposalMap = new TreeMap<>(Comparator.reverseOrder());

    public Proposals(final List<Proposal> proposals) {
        this.proposals = proposals;
        this.proposals.forEach(proposal -> {
            List<Proposal> proposalList = durationProposalMap.get(proposal.getDuration());
            if (proposalList == null) {
                proposalList = new ArrayList<>();
            }
            proposalList.add(proposal);
            durationProposalMap.put(proposal.getDuration(), proposalList);
        });
    }

    public static Proposal createProposal(final String title, final Duration duration) {
        return new Proposal(title, duration);
    }

    public List<Proposal> getProposalsForTheSlot(final Duration duration) {
        final List<Proposal> proposals = new ArrayList<>();
        populateProposalForSlot(duration, proposals);
        return proposals;
    }

    private void populateProposalForSlot(final Duration availableSlotDuration, final List<Proposal> proposals) {
        final Proposal proposal = getProposalOfHighestDurationLessThan(availableSlotDuration);
        if (proposal != null && !availableSlotDuration.isZero()) {
            removeProposal(proposal);
            proposals.add(proposal);
            populateProposalForSlot(availableSlotDuration.minus(proposal.getDuration()), proposals);
        }
    }

    public int numberOfProposals() {
        return durationProposalMap.size(); //what is this doing
    }


    private void removeProposal(final Proposal proposal) {
        final Duration key = proposal.duration;
        final List<Proposal> proposals = durationProposalMap.get(key);
        if (proposals != null) {
            proposals.remove(proposal);
        }
    }

    private Proposal getProposalOfHighestDurationLessThan(final Duration duration) {
        if (duration.isZero() || durationProposalMap.isEmpty()) {
            return null;
        } else {
            final Map.Entry<Duration, List<Proposal>> highestProposal;
            final Optional<Map.Entry<Duration, List<Proposal>>> highestEnty = durationProposalMap.entrySet()
                    .stream()
                    .filter(durationListEntry -> durationListEntry.getKey().compareTo(duration) <= 0)
                    .findFirst();
            if (highestEnty.isPresent()) {
                highestProposal = highestEnty.get();
            } else {
                return null;
            }
            final List<Proposal> proposals = highestProposal.getValue();
            if (proposals.size() == 1) {
                durationProposalMap.remove(highestProposal.getKey());
            }
            return proposals.get(0);
        }
    }


    public int getNumberOfTracks(final Duration totalTalkDuration) {
        final long totalProposedTalkDuraion = proposals.stream().mapToLong(proposal -> proposal.duration.toMinutes()).sum();
        return (int) Math.ceil(totalProposedTalkDuraion / (totalTalkDuration.toMinutes() * 1.0));
    }

    public static class Proposal {

        private final String title;
        private Duration duration;

        Proposal(final String title, final Duration duration) {
            this.title = title;
            this.duration = duration;
        }

        public Duration getDuration() {

            return duration;
        }

        public void setDuration(final Duration duration) {
            this.duration = duration;
        }

        public String getTitle() {
            return title;

        }
    }

}
