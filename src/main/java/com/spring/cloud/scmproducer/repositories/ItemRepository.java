package com.spring.cloud.scmproducer.repositories;

import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.spring.cloud.scmproducer.domain.Item;

import java.util.UUID;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {

    Page<Item> findAllByItemName(String itemName, Pageable pageable);

    Page<Item> findAllByItemType(ItemTypeEnum itemType, Pageable pageable);

    Page<Item> findAllByItemNameAndItemType(String itemName, ItemTypeEnum itemType, Pageable pageable);
}
