package com.spring.cloud.scmproducer.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.spring.cloud.scmproducer.domain.Item;

import java.util.UUID;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {
}
