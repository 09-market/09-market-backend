package com.gonggu.market.api.controller.dto.item;

import com.gonggu.market.api.controller.dto.comment.CommentInItemDto;
import com.gonggu.market.api.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailDto {
    private Long itemId;
    private String itemImageUrl;
    private String name;
    private String itemInfo;
    private Integer price;
    private Integer amount;
    private String category;
    private String instagramUrl;
    private int likes;
    private List<CommentInItemDto> comments = new ArrayList<>();
    private Long userId;
    private String userImageUrl;
    private String nickname;

    public ItemDetailDto() {
    }

    public ItemDetailDto(Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.itemImageUrl = item.getItemImageUrl();
        this.itemInfo = item.getItemInfo();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.category = item.getCategory().getCategoryName();
        this.instagramUrl = item.getInstagramUrl();
        this.likes = item.getLikes();
        item.getComments().forEach(comment -> {
            comments.add(new CommentInItemDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getItem().getId(),
                    comment.getUser().getNickname(),
                    comment.getUser().getId(),
                    comment.getUser().getUserImageUrl()
            ));
        });
        this.comments = comments;
        this.userId = item.getUser().getId();
        this.userImageUrl = item.getUser().getUserImageUrl();
        this.nickname = item.getUser().getNickname();
    }

    public ItemDetailDto(Long itemId, String itemImageUrl, String name, String itemInfo, Integer price, Integer amount, String category, String instagramUrl, int likes, List<CommentInItemDto> comments, Long userId, String userImageUrl, String nickname) {
        this.itemId = itemId;
        this.itemImageUrl = itemImageUrl;
        this.name = name;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.instagramUrl = instagramUrl;
        this.likes = likes;
        this.comments = comments;
        this.userId = userId;
        this.userImageUrl = userImageUrl;
        this.nickname = nickname;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<CommentInItemDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentInItemDto> comments) {
        this.comments = comments;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
