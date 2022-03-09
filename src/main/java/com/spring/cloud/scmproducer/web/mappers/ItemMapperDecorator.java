package com.spring.cloud.scmproducer.web.mappers;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.services.inventory.ItemInventoryService;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ItemMapperDecorator implements ItemMapper{
    private ItemInventoryService itemInventoryService;
    private ItemMapper mapper;

    @Autowired
    public void setItemInventoryService(ItemInventoryService itemInventoryService) {
        this.itemInventoryService = itemInventoryService;
    }

    @Autowired
    public void setMapper(ItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ItemDTO itemToItemDTO(Item item) {
        return mapper.itemToItemDTO(item);
    }

    @Override
    public ItemDTO itemToItemDTOwithInventory(Item item) {
        ItemDTO dto = mapper.itemToItemDTO(item);
        dto.setQuantityOnHand(itemInventoryService.getOnhandInventory(item.getId()));
        return dto;
    }

    @Override
    public Item itemDTOToItem(ItemDTO itemDto) {
        return mapper.itemDTOToItem(itemDto);
    }
}
