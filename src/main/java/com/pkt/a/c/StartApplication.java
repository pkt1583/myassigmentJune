package com.pkt.a.c;

import com.pkt.a.c.conference.Conference;
import com.pkt.a.c.io.input.DurationParser;
import com.pkt.a.c.io.output.ConferencePrinter;
import com.pkt.a.c.io.output.DefaultConferencePrinter;
import com.pkt.a.c.proposal.ProposalCreator;
import com.pkt.a.c.proposal.Proposals;
import com.pkt.a.c.scheduler.session.SessionSchedulerImpl;
import com.pkt.a.c.scheduler.track.ConferenceTrackSchedulerImpl;
import com.pkt.a.c.session.DefaultSessionsCreator;
import com.pkt.a.c.track.ConferenceTrackCreatorImpl;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StartApplication {
    public static void main(final String[] args) {
        final Options options = new Options();
        options.addRequiredOption("f", "filepath", true, "Input file path for eg: java start -f /home/pankaj/IdeaProjects/conferenceManagement/src/main/resources/input.txt");
        final CommandLineParser commandLineParser = new DefaultParser();
        final CommandLine commandLine;
        try {
            commandLine = commandLineParser.parse(options, args);
            final String filePath = commandLine.getOptionValue("f");
            try {
                final List<String> fileContent = IOUtils.readLines(new FileReader(new File(filePath)));
                final Proposals proposals = ProposalCreator.createProposals(()
                        -> fileContent.stream().map(line -> {
                    String[] splittedLine = line.split(" ");
                    String durationValue = splittedLine[splittedLine.length - 1];
                    Duration duration = DurationParser.parse(durationValue);
                    return Proposals.createProposal(line.replaceAll(durationValue, ""), duration);

                }).collect(Collectors.toList()));
                final Conference conference = new Conference(proposals, new ConferenceTrackCreatorImpl(new DefaultSessionsCreator() {
                }));
                conference.scheduleConferenceTalks(new ConferenceTrackSchedulerImpl(new SessionSchedulerImpl()));

                final ConferencePrinter conferencePrinter = new DefaultConferencePrinter(); //rename as stdoutconf filer
                conferencePrinter.printConferenceSchedule(new PrintWriter(System.out), conference);

                //TODO check if there is really scope of schedulign
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } catch (final ParseException e) {
            final HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("java -jar conferencemanagement.jar ", options);
        }
    }
}
