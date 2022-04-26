package com.gonggu.market.api.domain.item;

import com.gonggu.market.api.domain.BaseEntity;
import com.gonggu.market.api.domain.category.Category;

import javax.persistence.*;

@Entity
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String itemImageUrl;
    private String item_info;
    private int price;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    public Item(Long id, String name, String itemImageUrl, String item_info, int price, int amount, Category category) {
        this.id = id;
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.item_info = item_info;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public String getItem_info() {
        return item_info;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public void setItem_info(String item_info) {
        this.item_info = item_info;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
