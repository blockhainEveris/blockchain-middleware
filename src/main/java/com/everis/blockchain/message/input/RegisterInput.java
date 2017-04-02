package com.everis.blockchain.message.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterInput {

    private String enrollId;
    private String enrollSecret;


}
