package com.gonggu.market.api.domain.item;

import com.gonggu.market.api.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory(Category category);
    List<Item> findByNameContaining(String keyword);
}
