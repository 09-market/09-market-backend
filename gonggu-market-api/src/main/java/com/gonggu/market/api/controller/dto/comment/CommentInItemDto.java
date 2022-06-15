package com.gonggu.market.api.controller.dto.comment;

import com.gonggu.market.api.domain.item.Item;

public class CommentInItemDto {
    private String content;
    private Long itemId;
    private String nickname;
    private Long userId;
    private String userImageUrl;

    public CommentInItemDto() {
    }

    public CommentInItemDto(String content, Long itemId, String nickname, Long userId, String userImageUrl) {
        this.content = content;
        this.itemId = itemId;
        this.nickname = nickname;
        this.userId = userId;
        this.userImageUrl = userImageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
