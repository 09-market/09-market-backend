package com.gonggu.market.api.dto.user;

public class UserProfileDto {
    private String email;
    private String nickname;
    private String mobile;
    private String address;
    private String zipcode;
    private int point;

    public UserProfileDto() {
    }

    public UserProfileDto(String email, String nickname, String mobile, String address, String zipcode, int point) {
        this.email = email;
        this.nickname = nickname;
        this.mobile = mobile;
        this.address = address;
        this.zipcode = zipcode;
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
