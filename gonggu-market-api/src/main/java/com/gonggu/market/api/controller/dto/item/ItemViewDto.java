package com.gonggu.market.api.controller.dto.item;

public class ItemViewDto {
    private Long itemId;
    private String itemImageUrl;
    private String name;
    private int likes = 0;
    private int comments = 0;

    public ItemViewDto() {
    }

    public ItemViewDto(Long itemId, String itemImageUrl, String name, int likes, int comments) {
        this.itemId = itemId;
        this.itemImageUrl = itemImageUrl;
        this.name = name;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
