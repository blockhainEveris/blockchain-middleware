package com.everis.blockchain.functional;

import com.everis.blockchain.message.voting.Voter;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.message.voting.input.VoteInput;
import com.everis.blockchain.utils.MessageHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Random;

public class FunctionalHelper {

    public static String getRandomChannel() {
        final int max = 5;
        int random = new Random().nextInt(max);
        return "CHANNEL-" + random;
    }

    public static String prepareVoting(final int votingId) throws Exception {

        int random = MessageHelper.getRamdomAbs();

        final VoteInput voteInput = new VoteInput();
        voteInput.setVotingId(votingId);
        voteInput.setJustification("AutomaticTest-" + random);
        voteInput.setOptionId(new Random().nextInt(3) + 1);

        Voter voter = new Voter();
        voter.setName("name_" + random);
        voter.setSenderId(random + "");
        voter.setBoteId("boteId_" + random);
        voter.setConversationId("conversationId_" + random);
        voter.setChannel(getRandomChannel());
        voteInput.setVoter(voter);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(voteInput);

    }

    public static String prepareAddVoting() throws Exception {
        int random = MessageHelper.getRamdomAbs();
        final AddVotingInput params = new AddVotingInput("Desc_" + random, 3600, Arrays.asList("1", "2", "3"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(params);
    }
}
