package com.gonggu.market.api.service;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.category.Category;
import com.gonggu.market.api.domain.category.CategoryRepository;
import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.domain.item.ItemRepository;
import com.gonggu.market.api.dto.item.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(@Autowired ItemRepository itemRepository,
                       @Autowired CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Value("${file.path}")
    private String uploadFolder;

    public Item create(PrincipalDetails principalDetails, MultipartFile file, ItemDto dto) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        try {
            Files.write(imageFilePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Item item = new Item();
        item.setName(dto.getName());
        item.setItemImageUrl(imageFileName);
        item.setItem_info(dto.getItemInfo());
        item.setPrice(dto.getPrice());
        item.setAmount(dto.getAmount());
        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        item.setCategory(category);

        item = itemRepository.save(item);
        return item;
    }

    public List<ItemDto> readItemAll() {
        List<ItemDto> itemDtoList = new ArrayList<>();
        this.itemRepository.findAll().forEach(item -> {
            itemDtoList.add(new ItemDto(
                    item.getName(),
                    item.getItemImageUrl(),
                    item.getItem_info(),
                    item.getPrice(),
                    item.getAmount(),
                    item.getCategory().getCategoryName()
                    ));
        });
        return itemDtoList;
    }

    public List<ItemDto> readItemsByCategory(String category) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        this.itemRepository.findAllByCategory(category).forEach(item -> {
            itemDtoList.add(new ItemDto(
                    item.getName(),
                    item.getItemImageUrl(),
                    item.getItem_info(),
                    item.getPrice(),
                    item.getAmount(),
                    item.getCategory().getCategoryName()
            ));
        });
        return itemDtoList;
    }
}
