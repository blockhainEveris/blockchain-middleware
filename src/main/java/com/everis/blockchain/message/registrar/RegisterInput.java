package com.everis.blockchain.message.registrar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterInput {

    private String enrollId;
    private String enrollSecret;

}
