package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.bluemix.BluemixUser;
import com.everis.blockchain.constants.BlockChainConstants;
import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.exceptions.BlockChainException;
import com.everis.blockchain.exceptions.BlockChainValidationException;
import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.basic.Result;
import com.everis.blockchain.message.registrar.RegisterInput;
import com.everis.blockchain.message.registrar.RegisterOutput;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.utils.MessageHelper;
import com.everis.blockchain.validation.ValidationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@MicroserviceController
@RequestMapping(value = BlockChainConstants.ENDPOINT_ADMIN + "/*")
@Slf4j
public class AdminController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, value = BlockChainConstants.ENDPOINT_ADMIN_INFO)
    public List<BluemixUser> getBluemixData() throws BlockChainException {
        try {
            return bluemixData.getNodeUsers();
        } catch (Exception e) {
            throw new BlockChainException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = BlockChainConstants.ENDPOINT_ADMIN_LOGIN)
    public List<RegisterOutput> registrar() throws BlockChainException {
        try {
            List<RegisterOutput> list = new LinkedList<>();
            for (BluemixUser nodeUser : bluemixData.getNodeUsers()) {
                String peerUri = nodeUser.getPeer() + "/registrar";
                log.info("Call Method registrar in " + peerUri);
                RegisterInput input = new RegisterInput(nodeUser.getEnrollId(), nodeUser.getEnrollSecret());
                ResponseEntity<RegisterOutput> response = restTemplate.postForEntity(peerUri, input, RegisterOutput.class);
                log.info(response.getBody().toString());
                response.getBody().setNode(nodeUser.getPeer());
                list.add(response.getBody());
            }
            return list;
        } catch (Exception e) {
            throw new BlockChainException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = BlockChainConstants.ENDPOINT_ADMIN_CHAIN)
    public Result init() throws BlockChainException {
        try {
            BluemixUser bluemixUser = bluemixData.getRandomUser();
            String peerUri = bluemixUser.getPeer() + "/chaincode";
            log.info("Call Method chaincode in " + bluemixUser.getPeer());
            final int randomId = (int) System.currentTimeMillis();
            Init init = MessageHelper.prepareDeployChain(BlockChainConstants.BLUEMIX_CHAINCODE_URL, bluemixUser.getEnrollId(), randomId);
            ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
            response.getBody().getResult().setId(randomId);
            return response.getBody().getResult();
        } catch (Exception e) {
            throw new BlockChainException(e);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = BlockChainConstants.ENDPOINT_APPS_ADDVOTING)
    public Result addVoting(@RequestBody final AddVotingInput params) throws Exception {
        Errors errors = ValidationHelper.validate(validator, params);
        if (errors.getErrorCount() > 0) {
            throw new BlockChainValidationException(errors);
        }
        BluemixUser bluemixUser = bluemixData.getNodeUsers().get(0);
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        log.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareAddVotingMessage(codeChain, params, bluemixUser.getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        response.getBody().getResult().setId(randomId);
        return response.getBody().getResult();
    }

    @RequestMapping(method = RequestMethod.PUT, value = BlockChainConstants.ENDPOINT_APPS_UPDATE)
    public Result updateVotingStatus() throws Exception {
        BluemixUser bluemixUser = bluemixData.getNodeUsers().get(0);
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        log.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareUpdateVotingsStatusMessage(codeChain, bluemixUser.getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        return response.getBody().getResult();
    }

    @RequestMapping(method = RequestMethod.PUT, value = BlockChainConstants.ENDPOINT_APPS_UPDATE_VOTING)
    public Result updateVotingStatus(@PathVariable final int votingId) throws Exception {
        BluemixUser bluemixUser = bluemixData.getNodeUsers().get(0);
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        log.info("Call Method invoke in " + peerUri);
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareUpdateVotingStatusMessage(codeChain, bluemixUser.getEnrollId(), randomId, votingId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        return response.getBody().getResult();
    }


}
