
package com.everis.blockchain.bluemix;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BluemixUser {

    @JsonProperty("enrollId")
    private String enrollId;

    @JsonProperty("enrollSecret")
    private String enrollSecret;

    @JsonProperty("affiliation")
    private String affiliation;

    @JsonProperty("username")
    private String username;

    @JsonProperty("secret")
    private String secret;

    @JsonProperty("peer")
    private String peer;


}
