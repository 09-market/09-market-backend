package com.gonggu.market.api.dto.item;

public class ItemDto {
    private String name;
    private String itemImageUrl;
    private String itemInfo;
    private int price;
    private int amount;
    private String category;

    public ItemDto() {
    }

    public ItemDto(String name, String itemImageUrl, String itemInfo, int price, int amount, String category) {
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
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
}
