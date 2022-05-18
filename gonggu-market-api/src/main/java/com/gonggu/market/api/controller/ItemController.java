package com.gonggu.market.api.controller;

import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.controller.dto.item.ItemDto;
import com.gonggu.market.api.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ItemDto> createItem(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @RequestPart(value = "itemDto") ItemDto dto,
            @RequestHeader("Authorization") String token
    ) {
        logger.info("나 실행되니?");
        ItemDto result = itemService.create(file, dto, token);
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

    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId,
                                        @RequestPart(value = "file", required = false) MultipartFile file,
                                        @RequestPart(value = "itemDto") ItemDto dto,
                                        @RequestHeader("Authorization") String token) {
        this.itemService.update(itemId,file, dto, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId,
                                        @RequestHeader("Authorization") String token) {
        this.itemService.delete(itemId, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
