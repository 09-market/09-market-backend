package com.gonggu.market.api.dto.post;

public class InstagramFeedUploadDto {
    private String instagramUrl;
    private String hashtag;

    public InstagramFeedUploadDto() {
    }

    public InstagramFeedUploadDto(String instagramUrl, String hashtag) {
        this.instagramUrl = instagramUrl;
        this.hashtag = hashtag;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
