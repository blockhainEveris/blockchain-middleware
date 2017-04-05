package com.everis.blockchain.message.voting.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class AddVotingInput {

    @NotNull
    @Size(min = 5, max = 255)
    private String description;

    @NotNull
    @Min(3)
    @Max(3600)
    private int votingDeadlineInMinutes;

    @Valid
    @NotNull
    private List<String> options;

}
