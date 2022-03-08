package com.spring.cloud.scmproducer.services.inventory;

import com.spring.cloud.scmproducer.bootstrap.ItemLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled // utility for manual testing
@SpringBootTest
public class ItemInventoryServiceRestTemplateImplTest {

    @Autowired
    ItemInventoryService itemInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        Integer qoh = itemInventoryService.getOnhandInventory(UUID.fromString("42cbd2dc-b70c-4698-b564-80952d442990"));

        System.out.println(qoh);

    }
}
