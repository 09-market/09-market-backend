package com.gonggu.market.api.controller.dto.comment;

public class CommentDto {
    private String content;
    private Long itemId;
    private String nickname;

    public CommentDto() {
    }

    public CommentDto(String content, Long itemId, String nickname) {
        this.content = content;
        this.itemId = itemId;
        this.nickname = nickname;
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
}
