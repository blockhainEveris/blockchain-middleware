package com.everis.blockchain.controller;

import com.everis.blockchain.core.MicroserviceController;
import com.everis.blockchain.message.input.Register;
import com.everis.blockchain.utils.RestTemplatePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@MicroserviceController
public class LoginController {

    @Autowired
    private RestTemplatePost restTemplatePost;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private static final String URI = "https://3abcdc72e7174692ad27cdedb1bd7264-vp0.us.blockchain.ibm.com:5001/registrar";

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/blockchain/init")
    public String getVersion(@RequestBody Register register) throws Exception {
        LOGGER.info("login");
        return "login";
    }


}
