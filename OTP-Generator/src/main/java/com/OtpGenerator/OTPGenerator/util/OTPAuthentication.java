package com.OtpGenerator.OTPGenerator.util;

import com.OtpGenerator.OTPGenerator.entity.User;
import com.OtpGenerator.OTPGenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

@Component
public class OTPAuthentication {



    public String generateOtp(){
//        SplittableRandom splittableRandom =  new SplittableRandom();
//        StringBuilder sb =  new StringBuilder();
//
//        for (int i=0;i<=5;i++){
//            sb.append(splittableRandom.nextInt(0,10));
//        }
//        return  sb.toString();
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    public Long otpGeneratedTimeInMilliSecond()  {
        Date currentDate = new Date();
        Long optGeneatedTime = currentDate.getTime();
        return optGeneatedTime;
    }

    public String verifyUserOtp(User user)  {
        Long now = this.otpGeneratedTimeInMilliSecond();
        Long otpGeneratedTime = user.getOtpGeneratedTime();
        long diff = now-otpGeneratedTime;
        if(diff> TimeUnit.MINUTES.toMillis(5))
            return "expired OTP";
        else{
            return "verified OTP";
        }


    }
}
