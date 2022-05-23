package com.gonggu.market.api.controller.dto.comment;

public class CommentDto {
    private String content;
    private Long itemId;

    public CommentDto() {
    }

    public CommentDto(String content, Long itemId) {
        this.content = content;
        this.itemId = itemId;
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
}
