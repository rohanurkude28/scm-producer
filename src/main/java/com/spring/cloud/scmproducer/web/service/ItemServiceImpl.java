package com.spring.cloud.scmproducer.web.service;

import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService{
    @Override
    public ItemDTO getItemById(UUID itemId){
        return ItemDTO.builder().id(UUID.randomUUID()).itemName("Surf Excel Detergent").itemType(ItemTypeEnum.LAUNDRY).build();
    }

    @Override
    public ItemDTO saveNewItem(ItemDTO itemDTO) {
        return ItemDTO.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO, UUID itemId) {
        //TODO: Implement update Logic
        return null;
    }

    @Override
    public ItemDTO deleteItem(UUID itemId) {
        //TODO: Implement delete Logic
        log.info("Deleted Item: "+itemId);
        return null;
    }
}
