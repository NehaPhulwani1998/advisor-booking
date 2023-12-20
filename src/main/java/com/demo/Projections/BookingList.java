package com.demo.Projections;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface BookingList {

      String getAdName();
      String getUrl();
      BigInteger getAdId();
      BigInteger getBookingId();
      LocalDateTime getBookingTime();
}
