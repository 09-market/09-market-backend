package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.controller.dto.item.ItemRequestDto;
import com.gonggu.market.api.controller.dto.item.ItemViewDto;
import com.gonggu.market.api.domain.category.Category;
import com.gonggu.market.api.domain.category.CategoryRepository;
import com.gonggu.market.api.domain.item.Item;
import com.gonggu.market.api.domain.item.ItemRepository;
import com.gonggu.market.api.controller.dto.item.ItemDto;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ItemService(@Autowired ItemRepository itemRepository,
                       @Autowired CategoryRepository categoryRepository,
                       @Autowired UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public ItemRequestDto create(ItemRequestDto dto, String token) throws IOException {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();

        if (dto.getItemImageUrl() == null || dto.getItemImageUrl().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + dto.getItemImageName();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        byte[] decode = Base64.decodeBase64(dto.getItemImageUrl());
        FileOutputStream fos;
        File target = new File(imageFilePath.toString());
        target.createNewFile();
        fos = new FileOutputStream(target);
        fos.write(decode);
        fos.close();

        Item item = new Item();
        item.setName(dto.getName());
        item.setItemImageUrl(dto.getItemImageUrl());
        item.setItemInfo(dto.getItemInfo());
        item.setPrice(dto.getPrice());
        item.setAmount(dto.getAmount());
        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        item.setCategory(category);
        item.setInstagramUrl(dto.getInstagramUrl());
        User user = userRepository.findById(userIdFromToken).get();
        item.setUser(user);

        item = itemRepository.save(item);
        return new ItemRequestDto(item);
    }

    public List<ItemViewDto> readItemAll() {
        List<ItemViewDto> itemDtoList = new ArrayList<>();
        this.itemRepository.findAll().forEach(item -> {
            itemDtoList.add(new ItemViewDto(
                    item.getId(),
                    item.getItemImageUrl(),
                    item.getName(),
                    item.getLikes(),
                    item.getComments().size()
            ));
        });
        return itemDtoList;
    }

    public ItemDto readItemById(Long id) {
        Optional<Item> itemEntityOptional = this.itemRepository.findById(id);
        if (itemEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ItemDto(
                itemEntityOptional.get()
        );
    }

    public List<ItemViewDto> readItemsByCategory(String categoryName) {
        List<ItemViewDto> itemDtoList = new ArrayList<>();
        Category category = this.categoryRepository.findByCategoryName(categoryName);
        this.itemRepository.findAllByCategory(category).forEach(item -> {
            itemDtoList.add(new ItemViewDto(
                    item.getId(),
                    item.getItemImageUrl(),
                    item.getName(),
                    item.getLikes(),
                    item.getComments().size()
            ));
        });
        return itemDtoList;
    }

    public List<ItemDto> searchByName(String keword) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        itemRepository.findByNameContaining(keword).forEach(item -> {
            itemDtoList.add(new ItemDto(item));
        });
        return itemDtoList;
    }

    public void update(Long itemId, ItemRequestDto dto, String token) throws IOException {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        if (userRepository.findById(userIdFromToken).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Item itemEntity = itemRepository.findById(itemId).get();
        if (!itemEntity.getUser().equals(userRepository.findById(userIdFromToken).get())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (dto.getItemImageUrl() == null || dto.getItemImageUrl().equals("")) {
            itemEntity.setItemImageUrl(itemEntity.getItemImageUrl());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + dto.getItemImageName();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        byte[] decode = Base64.decodeBase64(dto.getItemImageUrl());
        FileOutputStream fos;
        File target = new File(imageFilePath.toString());
        target.createNewFile();
        fos = new FileOutputStream(target);
        fos.write(decode);
        fos.close();

        itemEntity.setItemImageUrl(dto.getItemImageUrl());
        itemEntity.setName(dto.getName() == null ? itemEntity.getName() : dto.getName());
        itemEntity.setItemInfo(dto.getItemInfo() == null ? itemEntity.getItemInfo() : dto.getItemInfo());
        itemEntity.setPrice(dto.getPrice() == null ? itemEntity.getPrice() : dto.getPrice());
        itemEntity.setAmount(dto.getAmount() == null ? itemEntity.getAmount() : dto.getAmount());


        if (dto.getCategory() != null) {
            Category category = categoryRepository.findByCategoryName(dto.getCategory());
            if (category == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            itemEntity.setCategory(category);
        } else {
            itemEntity.setCategory(itemEntity.getCategory());
        }
        itemEntity.setInstagramUrl(dto.getInstagramUrl() == null ? itemEntity.getInstagramUrl() : dto.getInstagramUrl());

        itemRepository.save(itemEntity);
    }

    public void delete(Long itemId, String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        if (userRepository.findById(userIdFromToken).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.itemRepository.existsById(itemId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Item itemEntity = itemRepository.findById(itemId).get();

        if (!itemEntity.getUser().equals(userRepository.findById(userIdFromToken).get())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        itemRepository.delete(itemEntity);
    }
}
