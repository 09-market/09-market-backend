package com.gonggu.market.api.controller.dto.item;

public class ItemDto {
    private String itemImageUrl;
    private String name;
    private String itemInfo;
    private int price;
    private int amount;
    private String category;
    private String instagramUrl;

    public ItemDto() {
    }

    public ItemDto(String name, String itemImageUrl, String itemInfo, int price, int amount, String category, String instagramUrl) {
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.itemImageUrl = itemImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemImageUrl='" + itemImageUrl + '\'' +
                ", name='" + name + '\'' +
                ", itemInfo='" + itemInfo + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", instagramUrl='" + instagramUrl + '\'' +
                '}';
    }
}
