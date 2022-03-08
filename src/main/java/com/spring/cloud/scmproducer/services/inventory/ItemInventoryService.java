package com.spring.cloud.scmproducer.services.inventory;

import java.util.UUID;

public interface ItemInventoryService {
    Integer getOnhandInventory(UUID itemId);
}
