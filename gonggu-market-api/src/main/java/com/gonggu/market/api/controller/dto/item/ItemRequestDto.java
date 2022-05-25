package com.gonggu.market.api.controller.dto.item;

import com.gonggu.market.api.domain.item.Item;

public class ItemRequestDto {
    private String itemImageUrl;
    private String itemImageName;
    private String name;
    private String itemInfo;
    private Integer price;
    private Integer amount;
    private String category;
    private String instagramUrl;

    public ItemRequestDto() {
    }

    public ItemRequestDto(String itemImageUrl, String itemImageName, String name, String itemInfo, Integer price, Integer amount, String category, String instagramUrl) {
        this.itemImageUrl = itemImageUrl;
        this.itemImageName = itemImageName;
        this.name = name;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.instagramUrl = instagramUrl;
    }

    public ItemRequestDto(Item item) {
        this.itemImageUrl = item.getItemImageUrl();
        this.name = item.getName();
        this.itemInfo = item.getItemInfo();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.category = item.getCategory().getCategoryName();
        this.instagramUrl = item.getInstagramUrl();
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getItemImageName() {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
}
