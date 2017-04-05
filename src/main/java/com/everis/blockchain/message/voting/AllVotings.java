
package com.everis.blockchain.message.voting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AllVotings {

    @JsonProperty("votings")
    private List<Voting> votings = null;

}
