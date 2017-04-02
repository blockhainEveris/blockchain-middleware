package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.bluemix.BluemixUser;
import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.message.input.RegisterInput;
import com.everis.blockchain.message.input.RegisterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@MicroserviceController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private BluemixData bluemixData;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/admin/bluemix")
    public BluemixData getBluemixData() throws Exception {
        return bluemixData;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/admin/bluemix/init")
    public RegisterOutput registrar(@RequestBody RegisterInput register) throws Exception {

        String peerUri = bluemixData.getPeers().get(0) + "/registrar";
        LOGGER.info("Call Method registrar in " + peerUri);

        BluemixUser adminUser = bluemixData.getAdminUser();
        RegisterInput input = new RegisterInput(adminUser.getEnrollId(), adminUser.getEnrollSecret());
        ResponseEntity<RegisterOutput> response = restTemplate.postForEntity(peerUri, input, RegisterOutput.class);
        LOGGER.info(response.getBody().toString());
        return response.getBody();
    }

}
