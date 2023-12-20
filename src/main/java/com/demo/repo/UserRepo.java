package com.demo.repo;

import com.demo.entity.AdvisorEntity;
import com.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, BigInteger> {

    UserEntity findByNameAndPassword(String name , String password);
}
