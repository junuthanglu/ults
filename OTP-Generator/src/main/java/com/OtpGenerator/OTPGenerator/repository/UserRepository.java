package com.OtpGenerator.OTPGenerator.repository;

import com.OtpGenerator.OTPGenerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String username, String password);
}
