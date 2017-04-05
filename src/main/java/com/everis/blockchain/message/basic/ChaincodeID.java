
package com.everis.blockchain.message.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ChaincodeID {

    @JsonProperty("path")
    private String path;

    @JsonProperty("name")
    private String name;


}
