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

    private AddVotingInput() {
    }

    public static final int MIN_DESC = 5;
    public static final int MAX_DESC = 255;

    public static final int MIN_MINUTES = 3;
    public static final int MAX_MINUTES = 3600;

    @NotNull
    @Size(min = MIN_DESC, max = MAX_DESC)
    private String description;

    @NotNull
    @Min(MIN_MINUTES)
    @Max(MAX_MINUTES)
    private int votingDeadlineInMinutes;

    @Valid
    @NotNull
    private List<String> options;

}
