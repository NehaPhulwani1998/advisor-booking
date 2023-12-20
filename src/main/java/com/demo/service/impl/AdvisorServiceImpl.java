package com.demo.service.impl;

import com.demo.Projections.BookingList;
import com.demo.config.AppConstants;
import com.demo.dto.request.RequestAddAdvisorDto;
import com.demo.dto.request.RequestBookingDto;
import com.demo.dto.request.RequestLoginUserDto;
import com.demo.dto.request.RequestRegisterUserDto;
import com.demo.dto.response.ResponseAdvisorDto;
import com.demo.dto.response.ResponseAdvisorList;
import com.demo.dto.response.ResponseBookingListDto;
import com.demo.dto.response.ResponseRegisterUserDto;
import com.demo.entity.AdvisorEntity;
import com.demo.entity.BookingEntity;
import com.demo.entity.UserEntity;
import com.demo.repo.AdvisorRepo;
import com.demo.repo.BookingRepo;
import com.demo.repo.UserRepo;
import com.demo.service.AdvisorService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@ComponentScan
public class AdvisorServiceImpl implements AdvisorService {

    @Autowired
    private AdvisorRepo advisorRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookingRepo bookingRepo;

    private static final Key secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);


    @Override
    public String addAdvisor(RequestAddAdvisorDto requestAddAdvisorDto) {

        try {
            if(requestAddAdvisorDto.getName() == null || requestAddAdvisorDto.getPhotoUrl() == null){
                return AppConstants.FAILURE;
            }

            AdvisorEntity advisorEntity = new AdvisorEntity();
            advisorEntity.setName(requestAddAdvisorDto.getName());
            advisorEntity.setUrl(requestAddAdvisorDto.getPhotoUrl());
            advisorRepo.save(advisorEntity);

            return AppConstants.SUCCESS;
        }catch (Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public ResponseRegisterUserDto registerUser(RequestRegisterUserDto requestRegisterUserDto) {

        ResponseRegisterUserDto response = null;

        String hashedPassword = hashPassword(requestRegisterUserDto.getPassword());

        UserEntity user = new UserEntity();
        user.setName(requestRegisterUserDto.getName());
        user.setPassword(hashedPassword);
        user.setEmail(requestRegisterUserDto.getEmail());
        userRepo.save(user);

        String jwtToken = generateToken(requestRegisterUserDto.getName(), 3600000);

        response = new ResponseRegisterUserDto();
        response.setJwtToken(jwtToken);
        response.setUserId(user.getUserId());
        return response;
    }

    @Override
    public ResponseRegisterUserDto loginUser(RequestLoginUserDto requestLoginUserDto) {

        ResponseRegisterUserDto response = null;
        String hashedPassword = hashPassword(requestLoginUserDto.getPassword());

        UserEntity user = userRepo.findByNameAndPassword(requestLoginUserDto.getName(), hashedPassword);

        if(user != null){
            response = new ResponseRegisterUserDto();
            String jwtToken = generateToken(requestLoginUserDto.getName(), 3600000);

            response.setUserId(user.getUserId());
            response.setJwtToken(jwtToken);
        }else {
            return null;
        }

        return response;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert bytes to hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateToken(String subject, long expirationMillis) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public ResponseAdvisorList getAdvisorList(BigInteger userId) {
        ResponseAdvisorList advisorList = new ResponseAdvisorList();
        List<ResponseAdvisorDto> responseAdvisorDtoList = new ArrayList<>();
        ResponseAdvisorDto responseAdvisorDto = null;

        List<AdvisorEntity> advisorEntities = advisorRepo.findAllByUserId(userId);

        for(AdvisorEntity advisor : advisorEntities){
            responseAdvisorDto = new ResponseAdvisorDto();
            responseAdvisorDto.setAdId(advisor.getAdId());
            responseAdvisorDto.setName(advisor.getName());
            responseAdvisorDto.setUrl(advisor.getUrl());
            responseAdvisorDtoList.add(responseAdvisorDto);

        }

        advisorList.setAdvisorList(responseAdvisorDtoList);

        return advisorList;
    }

    @Override
    public String bookAdvisor(RequestBookingDto requestBookingDto, BigInteger userId, BigInteger adId) {

        BookingEntity booking = new BookingEntity();
        booking.setBookingTime(requestBookingDto.getBookingTime());
        booking.setUserId(userId);
        booking.setAdId(adId);
        bookingRepo.save(booking);

        return AppConstants.SUCCESS;
    }

    @Override
    public List<ResponseBookingListDto> getBookingList(BigInteger userId) {

        List<ResponseBookingListDto> bookingList = new ArrayList<>();
        ResponseBookingListDto responseBookingListDto = null;

        List<BookingList> bookingListEntity = bookingRepo.getBookingList(userId);

        for(BookingList booking : bookingListEntity){
            responseBookingListDto = new ResponseBookingListDto();
            responseBookingListDto.setAdId(booking.getAdId());
            responseBookingListDto.setAdName(booking.getAdName());
            responseBookingListDto.setUrl(booking.getUrl());
            responseBookingListDto.setBookingId(booking.getBookingId());
            responseBookingListDto.setBookingTime(booking.getBookingTime());
            bookingList.add(responseBookingListDto);
        }
        return bookingList;
    }


}
