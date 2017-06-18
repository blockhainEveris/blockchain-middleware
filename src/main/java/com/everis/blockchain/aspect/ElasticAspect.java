package com.everis.blockchain.aspect;

import com.everis.blockchain.message.voting.input.VoteInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Aspect
@Slf4j
public class ElasticAspect {

    @Before("execution(* com.everis.blockchain.controller.AppsController.*(..)) && args(voteInput,..)")
    public void elasticAspectVoting(final VoteInput voteInput) {
        try {
            insertElasticVoting(voteInput);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void insertElasticVoting(VoteInput voteInput) throws Exception {


        Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("34.209.32.187"), 9300));

        log.info(client.settings().keySet().toString());

        esInsert(client, voteInput);
    }


    public String esInsert(TransportClient client, VoteInput voteInput) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(voteInput);

        IndexResponse response = client.prepareIndex("logstash-" + voteInput.getVotingId(), "voting")
                .setSource(jsonInString)
                .execute()
                .actionGet();
        log.info("Insert with id: " + response.getId());
        return response.getId();
    }

}
