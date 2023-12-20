package com.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "advisor")
@DynamicInsert
@DynamicUpdate
public class AdvisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @Column(name = "ad_id")
    private BigInteger adId;

    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "name")
    private String name;


    @Column(name = "photo_url")
    private String url;
}
