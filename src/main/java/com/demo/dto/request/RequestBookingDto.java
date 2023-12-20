package com.demo.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestBookingDto {

    private LocalDateTime bookingTime;

}
