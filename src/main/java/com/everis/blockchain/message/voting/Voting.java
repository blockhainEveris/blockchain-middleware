
package com.everis.blockchain.message.voting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "description", "voters", "options", "status", "voting_deadline_in_minutes", "start_voting_timestamp"})
@Data
public class Voting {

    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("voters")
    private List<Voter> voters = null;
    @JsonProperty("options")
    private List<Option> options = null;
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("voting_deadline_in_minutes")
    private String votingDeadlineInMinutes;
    @JsonProperty("start_voting_timestamp")
    private String startVotingTimestamp;


}
