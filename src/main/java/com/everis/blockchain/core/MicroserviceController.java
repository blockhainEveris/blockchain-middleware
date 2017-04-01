package com.everis.blockchain.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@RestController
public @interface MicroserviceController {

}