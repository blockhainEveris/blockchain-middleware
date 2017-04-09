package com.everis.blockchain.message.registrar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"OK"})
@Data
public class RegisterOutput {

    @JsonProperty("OK")
    private String ok;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("node")
    private String node;
}
