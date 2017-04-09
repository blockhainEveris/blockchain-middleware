
package com.everis.blockchain.message.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"function", "args"})
@Data
public class CtorMsg {

    @JsonProperty("function")
    private String function;
    @JsonProperty("args")
    private List<String> args = null;


}
