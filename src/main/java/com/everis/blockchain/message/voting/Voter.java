
package com.everis.blockchain.message.voting;

import com.everis.blockchain.constants.BlockChainConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "channel", "category", "office"})
@Data
public class Voter {

    public static final int MIN = 2;
    public static final int MAX = 255;

    @JsonProperty("id")
    @NotNull
    @Size(min = 1, max = Integer.MAX_VALUE)
    private String id;

    @NotNull
    @Size(min = MIN, max = MAX)
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("channel")
    @Size(min = 0, max = MAX)
    private String channel;

    @JsonProperty("category")
    @Size(min = 0, max = MAX)
    private String category = BlockChainConstants.NOT_APPLY;

    @JsonProperty("office")
    @Size(min = 0, max = MAX)
    private String office = BlockChainConstants.NOT_APPLY;


}
