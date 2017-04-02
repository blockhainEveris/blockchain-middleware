package com.everis.blockchain.message.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"OK"})
@Data
public class RegisterOutput {

    @JsonProperty("OK")
    private String OK;

    @JsonProperty("Error")
    private String Error;
}
