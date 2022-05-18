package com.gonggu.market.api.domain.item;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.category.Category;
import com.gonggu.market.api.domain.user.User;

import javax.persistence.*;

@Entity
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String itemImageUrl;
    private String itemInfo;
    private int price;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String instagramUrl;
    private int likes = 0;
    private int comments = 0;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

    public Item() {
    }

    public Item(Long id, String name, String itemImageUrl, String itemInfo, int price, int amount, Category category, String instagramUrl, int likes, int comments, User user) {
        this.id = id;
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.itemInfo = itemInfo;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.instagramUrl = instagramUrl;
        this.likes = likes;
        this.comments = comments;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
