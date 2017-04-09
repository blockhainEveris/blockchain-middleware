package com.everis.blockchain.controller;

import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class BaseController {

    protected String codeChain = "6b91d4470d2084bc5caf91ee3a8319e3d20de9f5ed819c8aeb90c4b509c62665860e844ad1d9f205e67f90c602175cba1ebb3552571a123dffc087aabff7f4ef";

    @Autowired
    protected BluemixData bluemixData;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected Validator validator;
}
