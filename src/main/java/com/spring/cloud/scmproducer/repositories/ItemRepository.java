package com.spring.cloud.scmproducer.repositories;

import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.spring.cloud.scmproducer.domain.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {

    Page<Item> findAllByItemName(String itemName, Pageable pageable);

    Page<Item> findAllByItemType(ItemTypeEnum itemType, Pageable pageable);

    Page<Item> findAllByItemNameAndItemType(String itemName, ItemTypeEnum itemType, Pageable pageable);

    @Query("SELECT I FROM Item I WHERE I.batchNo = :batchNo")
    List<Item> findByBatchNo(@Param("batchNo") Long batchNo);
}
