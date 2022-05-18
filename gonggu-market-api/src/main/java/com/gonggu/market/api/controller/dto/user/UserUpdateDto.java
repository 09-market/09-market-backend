package com.gonggu.market.api.controller.dto.user;

import com.gonggu.market.api.controller.dto.item.ItemDto;

import java.util.List;

public class UserUpdateDto {
    private String email;
    private String password;
    private String nickname;
    private String mobile;
    private String userImageUrl;
    private String userInfo;
    private String address;
    private String zipcode;
    private int point;
    private List<ItemDto> itemDtoList;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String email, String password, String nickname, String mobile, String userImageUrl, String userInfo, String address, String zipcode, int point, List<ItemDto> itemDtoList) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.userImageUrl = userImageUrl;
        this.userInfo = userInfo;
        this.address = address;
        this.zipcode = zipcode;
        this.point = point;
        this.itemDtoList = itemDtoList;
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

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
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

    public List<ItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", point=" + point +
                ", itemDtoList=" + itemDtoList +
                '}';
    }
}
