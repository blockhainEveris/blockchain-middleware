
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
    "discovery_host",
    "discovery_port",
    "api_host",
    "api_port_tls",
    "api_port",
    "event_host",
    "event_port",
    "type",
    "network_id",
    "container_id",
    "id",
    "api_url"
})
public class BluemixPeer {

    @JsonProperty("discovery_host")
    private String discoveryHost;
    @JsonProperty("discovery_port")
    private Integer discoveryPort;
    @JsonProperty("api_host")
    private String apiHost;
    @JsonProperty("api_port_tls")
    private Integer apiPortTls;
    @JsonProperty("api_port")
    private Integer apiPort;
    @JsonProperty("event_host")
    private String eventHost;
    @JsonProperty("event_port")
    private Integer eventPort;
    @JsonProperty("type")
    private String type;
    @JsonProperty("network_id")
    private String networkId;
    @JsonProperty("container_id")
    private String containerId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("api_url")
    private String apiUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("discovery_host")
    public String getDiscoveryHost() {
        return discoveryHost;
    }

    @JsonProperty("discovery_host")
    public void setDiscoveryHost(String discoveryHost) {
        this.discoveryHost = discoveryHost;
    }

    @JsonProperty("discovery_port")
    public Integer getDiscoveryPort() {
        return discoveryPort;
    }

    @JsonProperty("discovery_port")
    public void setDiscoveryPort(Integer discoveryPort) {
        this.discoveryPort = discoveryPort;
    }

    @JsonProperty("api_host")
    public String getApiHost() {
        return apiHost;
    }

    @JsonProperty("api_host")
    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    @JsonProperty("api_port_tls")
    public Integer getApiPortTls() {
        return apiPortTls;
    }

    @JsonProperty("api_port_tls")
    public void setApiPortTls(Integer apiPortTls) {
        this.apiPortTls = apiPortTls;
    }

    @JsonProperty("api_port")
    public Integer getApiPort() {
        return apiPort;
    }

    @JsonProperty("api_port")
    public void setApiPort(Integer apiPort) {
        this.apiPort = apiPort;
    }

    @JsonProperty("event_host")
    public String getEventHost() {
        return eventHost;
    }

    @JsonProperty("event_host")
    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    @JsonProperty("event_port")
    public Integer getEventPort() {
        return eventPort;
    }

    @JsonProperty("event_port")
    public void setEventPort(Integer eventPort) {
        this.eventPort = eventPort;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("network_id")
    public String getNetworkId() {
        return networkId;
    }

    @JsonProperty("network_id")
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    @JsonProperty("container_id")
    public String getContainerId() {
        return containerId;
    }

    @JsonProperty("container_id")
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("api_url")
    public String getApiUrl() {
        return apiUrl;
    }

    @JsonProperty("api_url")
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
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
