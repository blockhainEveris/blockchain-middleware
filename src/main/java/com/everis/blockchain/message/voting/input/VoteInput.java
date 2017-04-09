package com.everis.blockchain.message.voting.input;

import com.everis.blockchain.constants.BlockChainConstants;
import com.everis.blockchain.message.voting.Voter;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class VoteInput {

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int votingId;

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int optionId;

    private String justification = BlockChainConstants.NOT_APPLY;

    @NotNull
    @Valid
    private Voter voter;

}
