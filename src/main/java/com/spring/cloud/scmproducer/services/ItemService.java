package com.spring.cloud.scmproducer.services;

import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.model.ItemPagedList;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    ItemPagedList listsItems(String itemName, ItemTypeEnum itemType, PageRequest pageRequest, Boolean showInventoryOnHand);

    ItemDTO getItemById(UUID itemId, Boolean showInventoryOnHand);

    ItemDTO getItemByBatchNo(Long batchNo);

    ItemDTO saveNewItem(ItemDTO itemDTO);

    ItemDTO updateItemById(ItemDTO itemDTO, UUID itemId);

    ItemDTO deleteItemById(UUID itemId);
}
