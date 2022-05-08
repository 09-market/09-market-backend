package com.gonggu.market.api.dto.feed;

import com.gonggu.market.api.domain.tag.Tag;

public class FeedUploadDto {
    private String url;
    private String tags;

    public FeedUploadDto() {
    }

    public FeedUploadDto(String url, String tags) {
        this.url = url;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
