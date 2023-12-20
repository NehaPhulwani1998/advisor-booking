package com.demo.repo;

import com.demo.Projections.BookingList;
import com.demo.entity.BookingEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, BigInteger> {

    @Query(value = "SELECT ad.name as adName, ad.url as url , ad.adId as adId, be.bookingId as bookingId , be.bookingTime as bookingTime from BookingEntity be inner join AdvisorEntity ad on be.adId = ad.adId where be.userId =:userId")
    List<BookingList> getBookingList(@Value("userId") BigInteger userId);

}
