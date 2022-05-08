package com.gonggu.market.api.domain.hashtag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    public HashTag() {
    }

    public HashTag(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public HashTag(String tag) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
