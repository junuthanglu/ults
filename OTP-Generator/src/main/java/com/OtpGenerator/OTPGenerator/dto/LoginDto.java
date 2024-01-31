package com.OtpGenerator.OTPGenerator.dto;

public class LoginDto {

    String username;
    String password;
    String otp;
    String role;

    @Override
    public String toString() {
        return "loginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
