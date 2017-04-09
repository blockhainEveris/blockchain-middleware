
package com.everis.blockchain.message.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "chaincodeID", "ctorMsg", "secureContext"})
@Data
public class Params {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("chaincodeID")
    private ChaincodeID chaincodeID;
    @JsonProperty("ctorMsg")
    private CtorMsg ctorMsg;
    @JsonProperty("secureContext")
    private String secureContext;

}
