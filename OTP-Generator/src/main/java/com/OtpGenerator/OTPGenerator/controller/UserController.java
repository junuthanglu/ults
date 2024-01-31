package com.OtpGenerator.OTPGenerator.controller;

import com.OtpGenerator.OTPGenerator.dto.LoginDto;
import com.OtpGenerator.OTPGenerator.entity.User;
import com.OtpGenerator.OTPGenerator.service.UserService;
import com.OtpGenerator.OTPGenerator.util.OTPAuthentication;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OTPAuthentication otpAuthentication;

    @PostMapping("/")
    public ResponseEntity<String> register(@RequestBody @Valid User user) throws ParseException {
        userService.register(user);
        return new ResponseEntity<>(user.getName()+" is registered", HttpStatus.OK);
    }

    @GetMapping("/sendOtp")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Optional<User> u = userService.findUserByUserNameAndPassword(loginDto);
        if (u.isPresent()){
            String otp = userService.resendOtp(u.get().getId());
            return new ResponseEntity<>("Your Authentication otp is :"+ otp,HttpStatus.OK);
        }
        else
             return new ResponseEntity<>("The username and/or password that you have entered is incorrect",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<String> verifyOtp(@PathVariable Long id,@RequestBody LoginDto otp)  {

        String res = userService.verifyUserOtp(id,otp.getOtp());

       return new ResponseEntity<>(res,HttpStatus.OK);

    }
    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginDto loginDto){
        String res = userService.authethicateUser(loginDto);
      return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @GetMapping("/resend/{id}")
    public ResponseEntity<String> resendOtp(@PathVariable Long id){
        String otp = userService.resendOtp(id);
        return new ResponseEntity<>(otp,HttpStatus.OK);
    }

    @GetMapping("/autherization")
    public ResponseEntity<String> autherization(@RequestBody LoginDto loginDto){
        Optional<User> user = userService.findUserByUserNameAndPassword(loginDto);
        if(user.isPresent()) {
            if (userService.authorize(loginDto))
                return new ResponseEntity<>("Autherised Access with role : " + loginDto.getRole(),HttpStatus.OK);
            else return new ResponseEntity<>("Unautherised Access with role : " + loginDto.getRole(),HttpStatus.FORBIDDEN);
        }else return new ResponseEntity<>("NO User Found",HttpStatus.NOT_FOUND);

    }

}
