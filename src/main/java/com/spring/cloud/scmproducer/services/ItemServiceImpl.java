package com.spring.cloud.scmproducer.services;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.repositories.ItemRepository;
import com.spring.cloud.scmproducer.web.controller.NotFoundException;
import com.spring.cloud.scmproducer.web.mappers.ItemMapper;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.model.ItemPagedList;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Cacheable(cacheNames = "itemCache",key = "#itemId", condition = "#showInventoryOnHand == false")
    @Override
    public ItemDTO getItemById(UUID itemId, Boolean showInventoryOnHand) {
        Function<Item, ItemDTO> itemToItemDTOMapper = showInventoryOnHand ? itemMapper::itemToItemDTOwithInventory : itemMapper::itemToItemDTO;
        return itemToItemDTOMapper.apply(itemRepository.findById(itemId).orElseThrow(NotFoundException::new));
    }

    @Cacheable(cacheNames = "itemBatchListCache",key = "#batchNo")
    @Override
    public List<ItemDTO> getItemByBatchNo(Long batchNo) {
        return itemMapper.itemToItemDTO(itemRepository.findByBatchNo(batchNo));
    }

    @Override
    public ItemDTO saveNewItem(ItemDTO itemDTO) {
        return itemMapper.itemToItemDTO(itemRepository.save(itemMapper.itemDTOToItem(itemDTO)));
    }

    @Override
    public ItemDTO updateItemById(ItemDTO itemDTO, UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(NotFoundException::new);

        item.setItemName(itemDTO.getItemName());
        item.setItemType(itemDTO.getItemType());
        item.setPrice(itemDTO.getPrice());
        item.setBatchNo(itemDTO.getBatchNo());

        return itemMapper.itemToItemDTO(itemRepository.save(item));
    }

    @Override
    public ItemDTO deleteItemById(UUID itemId) {
        //TODO: Implement delete Logic
        log.info("Deleted Item: " + itemId);
        return null;
    }

    @Cacheable(cacheNames = "itemListCache", condition = "#showInventoryOnHand == false")
    @Override
    public ItemPagedList listsItems(String itemName, ItemTypeEnum itemType, PageRequest pageRequest, Boolean showInventoryOnHand) {
        Page<Item> itemPage;

        if (!StringUtils.isEmpty(itemName) && !StringUtils.isEmpty(itemType)) {
            //search both
            itemPage = itemRepository.findAllByItemNameAndItemType(itemName, itemType, pageRequest);
        } else if (!StringUtils.isEmpty(itemName) && StringUtils.isEmpty(itemType)) {
            //search item_service name
            itemPage = itemRepository.findAllByItemName(itemName, pageRequest);
        } else if (StringUtils.isEmpty(itemName) && !StringUtils.isEmpty(itemType)) {
            //search item_service type
            itemPage = itemRepository.findAllByItemType(itemType, pageRequest);
        } else {
            itemPage = itemRepository.findAll(pageRequest);
        }

        return new ItemPagedList(itemPage
                .getContent()
                .stream()
                .map(showInventoryOnHand ? itemMapper::itemToItemDTOwithInventory : itemMapper::itemToItemDTO) // Based on Parameter received
                .collect(Collectors.toList()),
                PageRequest
                        .of(itemPage.getPageable().getPageNumber(),
                                itemPage.getPageable().getPageSize()),
                itemPage.getTotalElements());
    }
}
