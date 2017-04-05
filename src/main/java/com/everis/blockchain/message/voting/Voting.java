
package com.everis.blockchain.message.voting;

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
    "id",
    "description",
    "voters",
    "options",
    "status",
    "voting_deadline_in_minutes",
    "start_voting_timestamp"
})
public class Voting {

    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("voters")
    private List<Voter> voters = null;
    @JsonProperty("options")
    private List<Option> options = null;
    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("voting_deadline_in_minutes")
    private String votingDeadlineInMinutes;
    @JsonProperty("start_voting_timestamp")
    private String startVotingTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("voters")
    public List<Voter> getVoters() {
        return voters;
    }

    @JsonProperty("voters")
    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    @JsonProperty("options")
    public List<Option> getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @JsonProperty("status")
    public Boolean getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("voting_deadline_in_minutes")
    public String getVotingDeadlineInMinutes() {
        return votingDeadlineInMinutes;
    }

    @JsonProperty("voting_deadline_in_minutes")
    public void setVotingDeadlineInMinutes(String votingDeadlineInMinutes) {
        this.votingDeadlineInMinutes = votingDeadlineInMinutes;
    }

    @JsonProperty("start_voting_timestamp")
    public String getStartVotingTimestamp() {
        return startVotingTimestamp;
    }

    @JsonProperty("start_voting_timestamp")
    public void setStartVotingTimestamp(String startVotingTimestamp) {
        this.startVotingTimestamp = startVotingTimestamp;
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
