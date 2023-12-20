package com.demo.dto.response;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ResponseAdvisorDto {

    private BigInteger adId;
    private String name;
    private String url;
}
