package com.gonggu.market.api.controller.dto.item;

import com.gonggu.market.api.domain.comment.Comment;
import com.gonggu.market.api.domain.item.Item;

import java.util.List;

public class ItemDto {
    private Long itemId;
    private String itemImageUrl;
    private String name;
    private String itemInfo;
    private Integer price;
    private Integer amount;
    private String category;
    private String instagramUrl;
    private String likes;
    private List<Comment> comments;

    public ItemDto() {
    }

    public ItemDto(Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.itemImageUrl = item.getItemImageUrl();
        this.itemInfo = item.getItemInfo();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.category = item.getCategory().getCategoryName();
        this.instagramUrl = item.getInstagramUrl();
        this.comments = item.getComments();
    }

    public ItemDto(Long itemId, String name, String itemImageUrl, String itemInfo, Integer price, Integer amount, String category, String instagramUrl) {
        this.itemId = itemId;
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.instagramUrl = instagramUrl;
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
