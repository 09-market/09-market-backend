package com.gonggu.market.api.dto.auth;

public class SignupDto {
    private String email;
    private String password;
    private String nickname;
    private String mobile;


    public SignupDto() {
    }

    public SignupDto(String email, String password, String nickname, String mobile) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
