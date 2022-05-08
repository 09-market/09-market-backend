package com.gonggu.market.api.service;

import com.gonggu.market.api.domain.feedtag.FeedTag;
import com.gonggu.market.api.domain.feedtag.FeedTagRepository;
import com.gonggu.market.api.domain.hashtag.HashTag;
import com.gonggu.market.api.domain.hashtag.HashTagRepository;
import com.gonggu.market.api.domain.post.InstagramFeed;
import com.gonggu.market.api.domain.post.InstagramFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedTagService {
    private final InstagramFeedRepository instagramFeedRepository;
    private final HashTagRepository hashTagRepository;
    private final FeedTagRepository feedTagRepository;

    public FeedTagService(@Autowired InstagramFeedRepository instagramFeedRepository,
                          @Autowired HashTagRepository hashTagRepository,
                          @Autowired FeedTagRepository feedTagRepository) {
        this.instagramFeedRepository = instagramFeedRepository;
        this.hashTagRepository = hashTagRepository;
        this.feedTagRepository = feedTagRepository;
    }


    public void saveTagInFeed(String hashTag, InstagramFeed instagramFeed) {
        String[] hashTagArray = hashTag.split(",");
        for (String tag : hashTagArray) {
            HashTag hashTagEntity = new HashTag();
            hashTagEntity.setType(tag);
            hashTagRepository.save(hashTagEntity);
            FeedTag feedTag = new FeedTag();
            feedTag.setFeed(instagramFeed);
            feedTag.setTag(hashTagEntity);
            feedTagRepository.save(feedTag);
        }
    }
}
