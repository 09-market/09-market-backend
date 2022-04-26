package com.gonggu.market.api.dto.user;

public class UserUpdateDto {
    private String password;
    private String nickname;
    private String mobile;
    private String address;
    private String zipcode;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String password, String nickname, String mobile, String address, String zipcode) {
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.address = address;
        this.zipcode = zipcode;
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
