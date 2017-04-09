
package com.everis.blockchain.message.basic;

import java.util.List;

import com.everis.blockchain.message.voting.Voting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "message"})
@Data
public class Result {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private Object message;

    @JsonProperty("votings")
    private List<Voting> votings;

    @JsonProperty("voting")
    private Voting voting;

    @JsonProperty("id")
    private Integer id;

}
