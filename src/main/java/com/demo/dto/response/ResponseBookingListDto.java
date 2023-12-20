package com.demo.dto.response;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class ResponseBookingListDto {

    private String adName;
    private String url;
    private BigInteger adId;
    private BigInteger bookingId;
    private LocalDateTime bookingTime;

}
