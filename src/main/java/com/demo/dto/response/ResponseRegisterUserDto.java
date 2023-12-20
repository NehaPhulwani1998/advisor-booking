package com.demo.dto.response;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ResponseRegisterUserDto {

    private String jwtToken;
    private BigInteger userId;

}
