package com.demo.controller;

import com.demo.config.AppConstants;
import com.demo.dto.request.RequestAddAdvisorDto;
import com.demo.dto.request.RequestBookingDto;
import com.demo.dto.request.RequestLoginUserDto;
import com.demo.dto.request.RequestRegisterUserDto;
import com.demo.dto.response.ResponseAdvisorList;
import com.demo.dto.response.ResponseBookingListDto;
import com.demo.dto.response.ResponseRegisterUserDto;
import com.demo.service.AdvisorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @Operation(summary = "Add an advisor")
    @PostMapping(AppConstants.ADD_ADVISOR)
    public ResponseEntity<String> addAdvisor(@RequestBody RequestAddAdvisorDto requestAddAdvisorDto) {

        String response = advisorService.addAdvisor(requestAddAdvisorDto);

        if(response.equals(AppConstants.SUCCESS)){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK);
        }
        else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Register User")
    @PostMapping(AppConstants.REGISTER_USER)
    public ResponseEntity<ResponseRegisterUserDto> registerUser(RequestRegisterUserDto requestRegisterUserDto){

        if(requestRegisterUserDto.getName() == null || requestRegisterUserDto.getPassword() == null || requestRegisterUserDto.getEmail() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ResponseRegisterUserDto responseRegisterUserDto = advisorService.registerUser(requestRegisterUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseRegisterUserDto);
    }

    @Operation(summary = "Register User")
    @PostMapping(AppConstants.REGISTER_USER)
    public ResponseEntity<ResponseRegisterUserDto> loginUser(RequestLoginUserDto requestLoginUserDto){

        if(requestLoginUserDto.getName() == null || requestLoginUserDto.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ResponseRegisterUserDto response = advisorService.loginUser(requestLoginUserDto);

        if(response == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @Operation(summary = "Get List of Advisor")
    @GetMapping("/user/{userId}/advisor")
    public ResponseEntity<ResponseAdvisorList> getAdvisorList(@PathVariable BigInteger userId) {
        // Fetch user details from the service based on the userId
        ResponseAdvisorList advisorList = advisorService.getAdvisorList(userId);

        if (advisorList != null) {
            return ResponseEntity.ok(advisorList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Book an advisor")
    @PostMapping("/user/{userId}/advisor/{adId}")
    public ResponseEntity<String> bookAdvisor(@RequestBody RequestBookingDto requestBookingDto, @PathVariable BigInteger userId, @PathVariable BigInteger adId) {

        String response = advisorService.bookAdvisor(requestBookingDto, userId, adId);

        if(response.equals(AppConstants.SUCCESS)){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK);
        }
        else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Get List of Bookings")
    @GetMapping("/user/{userId}/advisor/booking")
    public ResponseEntity<List<ResponseBookingListDto>> getBookingList(@PathVariable BigInteger userId) {
        // Fetch user details from the service based on the userId
        List<ResponseBookingListDto> bookingList = advisorService.getBookingList(userId);

        if (bookingList != null) {
            return ResponseEntity.ok(bookingList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
