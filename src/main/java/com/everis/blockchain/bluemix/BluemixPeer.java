
package com.everis.blockchain.bluemix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"discovery_host", "discovery_port", "api_host", "api_port_tls", "api_port",
        "event_host", "event_port", "type", "network_id", "container_id", "id", "api_url"
})
@Data
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


}
