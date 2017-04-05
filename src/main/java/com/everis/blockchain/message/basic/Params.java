
package com.everis.blockchain.message.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "chaincodeID",
        "ctorMsg",
        "secureContext"
})
public class Params {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("chaincodeID")
    private ChaincodeID chaincodeID;
    @JsonProperty("ctorMsg")
    private CtorMsg ctorMsg;
    @JsonProperty("secureContext")
    private String secureContext;

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("chaincodeID")
    public ChaincodeID getChaincodeID() {
        return chaincodeID;
    }

    @JsonProperty("chaincodeID")
    public void setChaincodeID(ChaincodeID chaincodeID) {
        this.chaincodeID = chaincodeID;
    }

    @JsonProperty("ctorMsg")
    public CtorMsg getCtorMsg() {
        return ctorMsg;
    }

    @JsonProperty("ctorMsg")
    public void setCtorMsg(CtorMsg ctorMsg) {
        this.ctorMsg = ctorMsg;
    }

    @JsonProperty("secureContext")
    public String getSecureContext() {
        return secureContext;
    }

    @JsonProperty("secureContext")
    public void setSecureContext(String secureContext) {
        this.secureContext = secureContext;
    }

}
