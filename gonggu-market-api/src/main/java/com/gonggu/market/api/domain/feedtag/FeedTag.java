package com.gonggu.market.api.domain.feedtag;

import com.gonggu.market.api.domain.hashtag.HashTag;
import com.gonggu.market.api.domain.post.InstagramFeed;

import javax.persistence.*;

@Entity
@Table(name = "feed_tag")
public class FeedTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instagram_feed_id")
    private InstagramFeed feed;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private HashTag tag;

    public FeedTag() {
    }

    public FeedTag(Long id, InstagramFeed feed, HashTag tag) {
        this.id = id;
        this.feed = feed;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstagramFeed getFeed() {
        return feed;
    }

    public void setFeed(InstagramFeed feed) {
        this.feed = feed;
    }

    public HashTag getTag() {
        return tag;
    }

    public void setTag(HashTag tag) {
        this.tag = tag;
    }
}
