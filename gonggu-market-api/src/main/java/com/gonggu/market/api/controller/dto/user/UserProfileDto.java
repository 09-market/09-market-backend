package com.gonggu.market.api.controller.dto.user;

import com.gonggu.market.api.domain.item.Item;

import java.util.List;

public class UserProfileDto {
    private Long userId;
    private String nickname;
    private String userImageUrl;
    private String userInfo;
    private List<Item> items;

    public UserProfileDto() {
    }

    public UserProfileDto(Long userId, String nickname, String userImageUrl, String userInfo, List<Item> items) {
        this.userId = userId;
        this.nickname = nickname;
        this.userImageUrl = userImageUrl;
        this.userInfo = userInfo;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
