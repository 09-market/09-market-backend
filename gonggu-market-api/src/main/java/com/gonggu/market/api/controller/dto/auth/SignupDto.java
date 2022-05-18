package com.gonggu.market.api.controller.dto.auth;

public class SignupDto {
    private String email;
    private String password;
    private String nickname;
    private String mobile;
    private String address;
    private String zipcode;

    public SignupDto() {
    }

    public SignupDto(String email, String nickname, String mobile, String address, String zipcode) {
        this.email = email;
        this.nickname = nickname;
        this.mobile = mobile;
        this.address = address;
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
