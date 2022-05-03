package com.gonggu.market.api.controller;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.dto.item.ItemDto;
import com.gonggu.market.api.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestPart(value = "file", required = true)MultipartFile file,
            @RequestPart(value = "itemDto")ItemDto dto
            ) {
        Item result = itemService.create(principalDetails, file, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<List<ItemDto>> readItemAll() {
        return ResponseEntity.ok(this.itemService.readItemAll());
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ItemDto>> readItems(@PathVariable String category) {
        return ResponseEntity.ok(this.itemService.readItemsByCategory(category));
    }
}
