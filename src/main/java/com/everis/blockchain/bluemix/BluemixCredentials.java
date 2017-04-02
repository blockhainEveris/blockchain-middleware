
package com.everis.blockchain.bluemix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "peers",
    "users",
    "cert",
    "cert_path"
})
public class BluemixCredentials {

    @JsonProperty("peers")
    private List<BluemixPeer> peers = null;
    @JsonProperty("users")
    private List<BluemixUser> users = null;
    @JsonProperty("cert")
    private String cert;
    @JsonProperty("cert_path")
    private String certPath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("peers")
    public List<BluemixPeer> getPeers() {
        return peers;
    }

    @JsonProperty("peers")
    public void setPeers(List<BluemixPeer> peers) {
        this.peers = peers;
    }

    @JsonProperty("users")
    public List<BluemixUser> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<BluemixUser> users) {
        this.users = users;
    }

    @JsonProperty("cert")
    public String getCert() {
        return cert;
    }

    @JsonProperty("cert")
    public void setCert(String cert) {
        this.cert = cert;
    }

    @JsonProperty("cert_path")
    public String getCertPath() {
        return certPath;
    }

    @JsonProperty("cert_path")
    public void setCertPath(String certPath) {
        this.certPath = certPath;
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
