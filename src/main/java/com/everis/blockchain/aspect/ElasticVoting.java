package com.everis.blockchain.aspect;

import com.everis.blockchain.message.voting.input.VoteInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ElasticVoting {

    @Autowired
    TransportClient transportClient;

    public String esInsert(VoteInput voteInput) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(voteInput);

        IndexResponse response = transportClient.prepareIndex("logstash-" + voteInput.getVotingId(), voteInput.getVotingId() + "")
                .setSource(jsonInString)
                .execute()
                .actionGet();
        log.info("Insert with id: " + response.getId());
        return response.getId();
    }
}
