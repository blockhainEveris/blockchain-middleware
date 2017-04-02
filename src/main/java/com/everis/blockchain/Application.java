package com.everis.blockchain;

import com.everis.blockchain.bluemix.BluemixCredentials;
import com.everis.blockchain.bluemix.BluemixData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.everis.blockchain.*"})
public class
Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplateBasic() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(30000);
        factory.setConnectTimeout(30000);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean
    public BluemixData blueMixCredentials() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Application.class.getResourceAsStream("/credentials.json");
        return new BluemixData(mapper.readValue(is, BluemixCredentials.class));
    }

}
