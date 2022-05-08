package com.gonggu.market.api.domain.feedtag;

import com.gonggu.market.api.domain.post.InstagramFeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedTagRepository extends JpaRepository<FeedTag, Long> {
}
