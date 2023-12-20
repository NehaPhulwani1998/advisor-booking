package com.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
@DynamicInsert
@DynamicUpdate
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @Column(name = "booking_id")
    private BigInteger bookingId;

    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "ad_id")
    private BigInteger adId;


    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

}
