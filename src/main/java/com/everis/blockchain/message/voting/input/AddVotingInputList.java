package com.everis.blockchain.message.voting.input;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class AddVotingInputList {

    @Valid
    private List<AddVotingInput> addVotingInputList;


}
