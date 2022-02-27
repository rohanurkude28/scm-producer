package com.spring.cloud.scmproducer.web.service;

import com.spring.cloud.scmproducer.web.model.ItemDTO;

import java.util.UUID;

public interface ItemService {
    ItemDTO getItemById(UUID itemId);

    ItemDTO saveNewItem(ItemDTO itemDTO);

    ItemDTO updateItem(ItemDTO itemDTO, UUID itemId);

    ItemDTO deleteItem(UUID itemId);
}
