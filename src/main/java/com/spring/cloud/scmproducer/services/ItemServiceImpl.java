package com.spring.cloud.scmproducer.services;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.repositories.ItemRepository;
import com.spring.cloud.scmproducer.web.controller.NotFoundException;
import com.spring.cloud.scmproducer.web.mappers.ItemMapper;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemDTO getItemById(UUID itemId){
        return itemMapper.itemToItemDTO(itemRepository.findById(itemId).orElseThrow(NotFoundException::new));
        //return ItemDTO.builder().id(UUID.randomUUID()).itemName("Surf Excel Detergent").itemType(ItemTypeEnum.LAUNDRY).build();
    }

    @Override
    public ItemDTO saveNewItem(ItemDTO itemDTO) {
        return itemMapper.itemToItemDTO(itemRepository.save(itemMapper.itemDTOToItem(itemDTO)));
        //return ItemDTO.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public ItemDTO updateItemById(ItemDTO itemDTO, UUID itemId) {
        Item item=itemRepository.findById(itemId).orElseThrow(NotFoundException::new);

        item.setItemName(itemDTO.getItemName());
        item.setItemType(itemDTO.getItemType());
        item.setPrice(itemDTO.getPrice());
        item.setBatchNo(itemDTO.getBatchNo());

        return itemMapper.itemToItemDTO(itemRepository.save(item));
    }

    @Override
    public ItemDTO deleteItemById(UUID itemId) {
        //TODO: Implement delete Logic
        log.info("Deleted Item: "+itemId);
        return null;
    }
}
