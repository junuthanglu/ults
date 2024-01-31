package com.OtpGenerator.OTPGenerator.service;

import com.OtpGenerator.OTPGenerator.dto.LoginDto;
import com.OtpGenerator.OTPGenerator.entity.User;
import com.OtpGenerator.OTPGenerator.repository.UserRepository;
import com.OtpGenerator.OTPGenerator.util.OTPAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPAuthentication otpAuthentication;
    public void register(User user) {
        userRepository.save(user);
    }
    public void updateUser(User user){
        userRepository.save(user);

    }

    public Optional<User> findUser(Long userId){
        return userRepository.findById(userId);
    }

    public String verifyUserOtp(Long userId,String otp){
        Optional<User> u = this.findUser(userId);

        if(u.isPresent()){
            String usrotp = u.get().getOtp();
            String currentOtp =otp;
            if(u.get().getOtp().equals(otp)) {
                String res = otpAuthentication.verifyUserOtp(u.get());
                if (res.equals("verified OTP")) {
                    u.get().setAuthenticated(true);
                    this.updateUser(u.get());
                }
                return res;
            }else return "incorrect OTP";

        }
        return "user not found";
    }

    public String resendOtp(Long id) {
        Optional<User> u = this.findUser(id);
        if(u.isPresent()){
            String otp = otpAuthentication.generateOtp();
            u.get().setOtp(otp);
            u.get().setOtpGeneratedTime(otpAuthentication.otpGeneratedTimeInMilliSecond());
            this.updateUser(u.get());
            return otp;
        }
        return "NO user found";

    }

    public Optional<User> findUserByUserNameAndPassword(LoginDto loginDto) {
        return userRepository.findByEmailAndPassword(loginDto.getUsername(),loginDto.getPassword());
    }

    public String authethicateUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (user.isPresent()){
            if(user.get().getAuthenticated())
                return "Authenticated";
        }
        return "Authentication failed, Verification needed!";
    }


    public Boolean authorize(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if(user.isPresent()){
            if(user.get().getAuthenticated() && user.get().getRole().getRole().equals(loginDto.getRole()))
                return true;
            else
                return false;

        }else return false;
    }
}
