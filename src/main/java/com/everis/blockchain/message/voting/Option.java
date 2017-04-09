
package com.everis.blockchain.message.voting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "description", "number_of_votes", "votes"})
@Data
public class Option {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("number_of_votes")
    private Integer numberOfVotes;
    @JsonProperty("votes")
    private Object votes;


}
