package com.demo.repo;

import com.demo.entity.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AdvisorRepo extends JpaRepository<AdvisorEntity, BigInteger> {

    List<AdvisorEntity> findAllByUserId(BigInteger userId);
}
