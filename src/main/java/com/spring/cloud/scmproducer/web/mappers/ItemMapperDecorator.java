package com.spring.cloud.scmproducer.web.mappers;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.services.inventory.ItemInventoryService;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
    public List<ItemDTO> itemToItemDTO(List<Item> items) {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        items.forEach(item -> itemDTOS.add(mapper.itemToItemDTO(item)));
        return itemDTOS;
    }

    @Override
    public Item itemDTOToItem(ItemDTO itemDto) {
        return mapper.itemDTOToItem(itemDto);
    }
}
