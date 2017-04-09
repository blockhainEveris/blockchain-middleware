package com.everis.blockchain;

import com.everis.blockchain.bluemix.BluemixCredentials;
import com.everis.blockchain.bluemix.BluemixData;
import com.everis.blockchain.constants.BlockChainConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.everis.blockchain.*"})
@EnableAdminServer
@Slf4j
public class Application {

    public static void main(String[] args) {
        //setEverisProxy();
        SpringApplication.run(Application.class, args);
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
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(10000);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean
    public BluemixData blueMixCredentials() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Application.class.getResourceAsStream("/credentials.json");
        return new   BluemixData(mapper.readValue(is, BluemixCredentials.class));
    }

}
