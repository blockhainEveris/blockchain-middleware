package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.bluemix.BluemixUser;
import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.utils.MessageHelper;
import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.basic.Result;
import com.everis.blockchain.message.registrar.RegisterInput;
import com.everis.blockchain.message.registrar.RegisterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@MicroserviceController
@RequestMapping(value = "/api/v1/admin/blockchain/*")
public class AdminController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private String codeChain = "a35d99ec889abc2b1b92fe5e3abbdcdbc5f1ad2da2c9dc55fb325485f90b25f06692f1c55b6e84f2e089f91c2eddc1339537b7f82ec6bd2f9896d21f9df2fe7b";

    @RequestMapping(method = RequestMethod.GET, value = "/bluemix")
    public BluemixData getBluemixData() throws Exception {
        return bluemixData;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/unregister")
    public List<RegisterOutput> deleteRegister() throws Exception {
        List<RegisterOutput> outputList = new LinkedList<>();
        for (String baseUri : bluemixData.getPeers()) {
            RegisterOutput output = new RegisterOutput();
            try {
                final String uri = baseUri + "/registrar/{user}";
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", bluemixData.getAdminUser().getEnrollId());
                restTemplate.delete(uri, params);
                output.setOk(uri);

            } catch (RestClientException e) {
                output.setError(e.getMessage());
                output.setOk(baseUri);
            }
            outputList.add(output);
        }
        return outputList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RegisterOutput registrar() throws Exception {
        String peerUri = bluemixData.getPeers().get(0) + "/registrar";
        LOGGER.info("Call Method registrar in " + peerUri);

        BluemixUser adminUser = bluemixData.getAdminUser();
        RegisterInput input = new RegisterInput(adminUser.getEnrollId(), adminUser.getEnrollSecret());
        ResponseEntity<RegisterOutput> response = restTemplate.postForEntity(peerUri, input, RegisterOutput.class);
        LOGGER.info(response.getBody().toString());
        return response.getBody();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chaincode")
    public Result init() throws Exception {
        String peerUri = bluemixData.getPeers().get(0) + "/chaincode";
        LOGGER.info("Call Method registrar in " + peerUri);
        String path = "https://github.com/osamso/learn-chaincode/start";
        final int randomId = (int) System.currentTimeMillis();
        Init init = MessageHelper.prepareDeployChain(path, bluemixData.getAdminUser().getEnrollId(), randomId);
        ResponseEntity<Init> response = restTemplate.postForEntity(peerUri, init, Init.class);
        response.getBody().getResult().setId(randomId);
        return response.getBody().getResult();
    }


}
