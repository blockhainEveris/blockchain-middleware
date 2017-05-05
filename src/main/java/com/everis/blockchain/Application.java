package com.everis.blockchain;

import com.everis.blockchain.bluemix.BluemixCredentials;
import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.constants.BlockChainConstants;
import com.everis.blockchain.utils.MessageHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.everis.blockchain.*"})
@Slf4j
public class Application {

    private static final int TIMEOUT = 60000;

    public static void main(final String[] args) {
        setEverisProxy();
        SpringApplication.run(Application.class, args);
        log.info("ChainCode: " + MessageHelper.getChainCodeTmpFile());
    }

    private static void setEverisProxy() {
        if (BlockChainConstants.EVERIS_PROXY) {
            log.info("setEverisProxy...");
            Properties props = System.getProperties();
            props.put("http.proxyHost", BlockChainConstants.EVERIS_PROXY_HOST);
            props.put("http.proxyPort", BlockChainConstants.EVERIS_PROXY_PORT);
            props.put("https.proxyHost", BlockChainConstants.EVERIS_PROXY_HOST);
            props.put("https.proxyPort", BlockChainConstants.EVERIS_PROXY_PORT);
        }
    }

    @Bean
    public RestTemplate restTemplateBasic() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(TIMEOUT);
        factory.setConnectTimeout(TIMEOUT);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean
    public BluemixData blueMixCredentials() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Application.class.getResourceAsStream("/credentials.json");
        return new BluemixData(mapper.readValue(is, BluemixCredentials.class));
    }

}
