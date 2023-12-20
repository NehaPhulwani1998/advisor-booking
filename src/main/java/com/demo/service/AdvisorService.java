package com.demo.service;

import com.demo.dto.request.RequestAddAdvisorDto;
import com.demo.dto.request.RequestBookingDto;
import com.demo.dto.request.RequestLoginUserDto;
import com.demo.dto.request.RequestRegisterUserDto;
import com.demo.dto.response.ResponseAdvisorDto;
import com.demo.dto.response.ResponseAdvisorList;
import com.demo.dto.response.ResponseBookingListDto;
import com.demo.dto.response.ResponseRegisterUserDto;

import java.math.BigInteger;
import java.util.List;

public interface AdvisorService{

    String addAdvisor(RequestAddAdvisorDto requestAddAdvisorDto);

    ResponseRegisterUserDto registerUser(RequestRegisterUserDto requestRegisterUserDto);


    ResponseRegisterUserDto loginUser(RequestLoginUserDto requestLoginUserDto);

    ResponseAdvisorList getAdvisorList(BigInteger userId);

    String bookAdvisor(RequestBookingDto requestBookingDto, BigInteger userId, BigInteger adId);

    List<ResponseBookingListDto> getBookingList(BigInteger userId);
}
