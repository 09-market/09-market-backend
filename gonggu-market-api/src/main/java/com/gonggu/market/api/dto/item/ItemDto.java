package com.gonggu.market.api.dto.item;

public class ItemDto {
    private Long itemId;
    private String name;
    private String itemImageUrl;
    private String itemInfo;
    private int price;
    private int amount;
    private String category;
    private int likes = 0;
    private int comments = 0;

    public ItemDto() {
    }

    public ItemDto(Long itemId, String name, String itemImageUrl, String itemInfo, int price, int amount, String category) {
        this.itemId = itemId;
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
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
