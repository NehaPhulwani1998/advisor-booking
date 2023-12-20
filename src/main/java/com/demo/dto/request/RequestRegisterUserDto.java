package com.demo.dto.request;

import lombok.Data;

@Data
public class RequestRegisterUserDto {

    private String name;
    private String password;
    private String email;

}
