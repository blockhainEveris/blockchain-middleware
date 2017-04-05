package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BaseController {

    @Autowired
    protected BluemixData bluemixData;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected Validator validator;
}
