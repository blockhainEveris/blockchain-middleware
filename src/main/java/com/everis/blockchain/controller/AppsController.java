package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixUser;
import com.everis.blockchain.constants.BlockChainConstants;
import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.exceptions.BlockChainException;
import com.everis.blockchain.exceptions.BlockChainValidationException;
import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.basic.Result;
import com.everis.blockchain.message.voting.AllVotings;
import com.everis.blockchain.message.voting.Voter;
import com.everis.blockchain.message.voting.Voting;
import com.everis.blockchain.message.voting.input.VoteInput;
import com.everis.blockchain.utils.MessageHelper;
import com.everis.blockchain.validation.ValidationHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@MicroserviceController
@RequestMapping(value = BlockChainConstants.ENDPOINT_APPS + "/*")
@Slf4j
public class AppsController extends BaseController {

    @RequestMapping(method = RequestMethod.PUT, value = BlockChainConstants.ENDPOINT_APPS_VOTE)
    public Result vote(@RequestBody final VoteInput params) throws Exception {

        //TODO: WorkAround Checkpoint2
        Voter voter = params.getVoter();
        if(StringUtils.isEmpty(voter.getName())){
            params.getVoter().setName("Name_"+ System.currentTimeMillis());
        }

        if(StringUtils.isEmpty(voter.getSenderId())){
            params.getVoter().setSenderId("senderID_"+ System.currentTimeMillis());
        }

        Errors errors = ValidationHelper.validate(validator, params);
        if (errors.getErrorCount() > 0) {
            throw new BlockChainValidationException(errors);
        }
        BluemixUser bluemixUser = bluemixData.getRandomUser();
        String peerUri = bluemixUser.getPeer() + "/chaincode";
        log.info("Call Method invoke in " + bluemixUser.getPeer());
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareVoteMessage(getCodeChain(), params, bluemixUser.getEnrollId());
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        response.getBody().getResult().setId(randomId);
        return response.getBody().getResult();
    }

    @RequestMapping(method = RequestMethod.GET, value = BlockChainConstants.ENDPOINT_APPS_QUERY_VOTING)
    public Result query(@PathVariable final int votingId) throws BlockChainException {
        Errors errors = ValidationHelper.validate(validator, votingId);
        if (errors.getErrorCount() > 0) {
            throw new BlockChainValidationException(errors);
        }
        BluemixUser bluemixUser = bluemixData.getRandomUser();
        String peerUri = bluemixUser.getPeer() + "/chaincode";
        log.info("Call Method chaincode in " + bluemixUser.getPeer());
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareQueryVotingMessage(getCodeChain(), bluemixUser.getEnrollId(), randomId, votingId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        ObjectMapper mapper = new ObjectMapper();

        try {
            Voting voting = mapper.readValue((String) response.getBody().getResult().getMessage(), Voting.class);
            response.getBody().getResult().setMessage(null);
            response.getBody().getResult().setVoting(voting);
            return response.getBody().getResult();
        } catch (Exception e) {
            throw new BlockChainException("Voting not found");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BlockChainConstants.ENDPOINT_APPS_QUERY)
    public Result query() throws Exception {
        BluemixUser bluemixUser = bluemixData.getRandomUser();
        String peerUri = bluemixUser.getPeer() + "/chaincode";
        log.info("Call Method invoke in " + bluemixUser.getPeer());

        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareQueryMessage(getCodeChain(), bluemixUser.getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);

        ObjectMapper mapper = new ObjectMapper();
        AllVotings all = mapper.readValue((String) response.getBody().getResult().getMessage(), AllVotings.class);
        response.getBody().getResult().setVotings(all.getVotings());
        response.getBody().getResult().setMessage(null);

        return response.getBody().getResult();

    }


}
