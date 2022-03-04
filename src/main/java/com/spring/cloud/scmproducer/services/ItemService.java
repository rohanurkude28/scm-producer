package com.spring.cloud.scmproducer.services;

import com.spring.cloud.scmproducer.web.model.ItemDTO;

import java.util.UUID;

public interface ItemService {
    ItemDTO getItemById(UUID itemId);

    ItemDTO saveNewItem(ItemDTO itemDTO);

    ItemDTO updateItemById(ItemDTO itemDTO, UUID itemId);

    ItemDTO deleteItemById(UUID itemId);
}
