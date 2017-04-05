package com.everis.blockchain.message.voting.input;

import com.everis.blockchain.message.voting.Voter;
import lombok.Data;

@Data
public class VoteInput {

    private int votingId;
    private int optionId;
    private String justification;
    private Voter voter;

    // votingId, optionId, justification, id, name, category, office, channel
    //     0        1      	     2         3    4      5	    6	     7
    // ["1", "2", "Justification", "1", "Peter", "SKL", "Barcelona", "Twitter"]
}
