package com.everis.blockchain.controller;

import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.exceptions.BlockChainValidationException;
import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.basic.Result;
import com.everis.blockchain.message.voting.Voter;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.message.voting.AllVotings;
import com.everis.blockchain.message.voting.input.VoteInput;
import com.everis.blockchain.utils.MessageHelper;
import com.everis.blockchain.validation.ValidationHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@MicroserviceController
@RequestMapping(value = "/api/v1/apps/blockchain/*")
public class AppsController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppsController.class);
    private String codeChain = "a35d99ec889abc2b1b92fe5e3abbdcdbc5f1ad2da2c9dc55fb325485f90b25f06692f1c55b6e84f2e089f91c2eddc1339537b7f82ec6bd2f9896d21f9df2fe7b";

    @RequestMapping(method = RequestMethod.POST, value = "/addvoting")
    public Result addVoting(@RequestBody final AddVotingInput params) throws Exception {
        Errors errors = ValidationHelper.validate(validator, params);
        if (errors.getErrorCount() > 0) {
            throw new BlockChainValidationException(errors);
        }

        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        LOGGER.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareAddVotingMessage(codeChain, params, bluemixData.getAdminUser().getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        response.getBody().getResult().setId(randomId);
        return response.getBody().getResult();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/vote")
    public Result vote(@RequestBody final VoteInput params) throws Exception {
        Errors errors = ValidationHelper.validate(validator, params);
        if (errors.getErrorCount() > 0) {
            throw new BlockChainValidationException(errors);
        }
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        LOGGER.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareVoteMessage(codeChain, params, bluemixData.getAdminUser().getEnrollId());
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        response.getBody().getResult().setId(randomId);
        return response.getBody().getResult();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public Result query() throws Exception {

        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        LOGGER.info("Call Method invoke in " + peerUri);

        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareQueryMessage(codeChain, bluemixData.getAdminUser().getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);

        ObjectMapper mapper = new ObjectMapper();
        AllVotings all = mapper.readValue((String) response.getBody().getResult().getMessage(), AllVotings.class);
        response.getBody().getResult().setVotings(all.getVotings());
        response.getBody().getResult().setMessage(null);

        return response.getBody().getResult();

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Result updateVotingStatus() throws Exception {
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        LOGGER.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareUpdateVotingStatusMessage(codeChain, bluemixData.getAdminUser().getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        return response.getBody().getResult();
    }


}
