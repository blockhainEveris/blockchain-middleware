
package com.everis.blockchain.bluemix;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "enrollId",
    "enrollSecret",
    "affiliation",
    "username",
    "secret"
})
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("enrollId")
    public String getEnrollId() {
        return enrollId;
    }

    @JsonProperty("enrollId")
    public void setEnrollId(String enrollId) {
        this.enrollId = enrollId;
    }

    @JsonProperty("enrollSecret")
    public String getEnrollSecret() {
        return enrollSecret;
    }

    @JsonProperty("enrollSecret")
    public void setEnrollSecret(String enrollSecret) {
        this.enrollSecret = enrollSecret;
    }

    @JsonProperty("affiliation")
    public String getAffiliation() {
        return affiliation;
    }

    @JsonProperty("affiliation")
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
