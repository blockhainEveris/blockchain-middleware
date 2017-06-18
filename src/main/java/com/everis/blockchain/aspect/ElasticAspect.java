package com.everis.blockchain.aspect;

import com.everis.blockchain.message.voting.input.VoteInput;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ElasticAspect {

    @Autowired
    private ElasticVoting elasticVoting;

    @Before("execution(* com.everis.blockchain.controller.AppsController.*(..)) && args(voteInput,..)")
    public void elasticAspectVoting(final VoteInput voteInput) {
        try {
            elasticVoting.esInsert(voteInput);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
