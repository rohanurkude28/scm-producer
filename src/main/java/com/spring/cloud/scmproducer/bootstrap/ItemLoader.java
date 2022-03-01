package com.spring.cloud.scmproducer.bootstrap;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.repositories.ItemRepository;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Slf4j
@Component
public class ItemLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public ItemLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadItems();
    }

    private void loadItems() {
        if (itemRepository.count() == 0) {

            itemRepository.save(Item.builder()
                    .itemName("Bread")
                    .itemType(ItemTypeEnum.BAKERY)
                    .quantityToProduce(20)
                    .minOnHand(5)
                    .price(new BigDecimal("19.99"))
                    .batchNo(createRandomBatchNumber())
                    .build());

            itemRepository.save(Item.builder()
                    .itemName("Surf Excel")
                    .itemType(ItemTypeEnum.LAUNDRY)
                    .quantityToProduce(200)
                    .minOnHand(15)
                    .price(new BigDecimal("79.99"))
                    .batchNo(createRandomBatchNumber())
                    .build());

            itemRepository.save(Item.builder()
                    .itemName("Diet Coke")
                    .itemType(ItemTypeEnum.BEVERAGES)
                    .quantityToProduce(150)
                    .minOnHand(20)
                    .price(new BigDecimal("9.99"))
                    .batchNo(createRandomBatchNumber())
                    .build());

            itemRepository.save(Item.builder()
                    .itemName("Maggi")
                    .itemType(ItemTypeEnum.CANNED_GOODS)
                    .quantityToProduce(500)
                    .minOnHand(150)
                    .price(new BigDecimal("4.99"))
                    .batchNo(createRandomBatchNumber())
                    .build());

            itemRepository.save(Item.builder()
                    .itemName("Kellogs CornFlakes")
                    .itemType(ItemTypeEnum.CEREAL)
                    .quantityToProduce(200)
                    .minOnHand(50)
                    .price(new BigDecimal("14.99"))
                    .batchNo(createRandomBatchNumber())
                    .build());
        }
        log.debug("Items Loaded : "+ itemRepository.count());
    }

    private Long createRandomBatchNumber() {
        Random ran = new Random();
        return Long.valueOf(ran.nextInt(6) + 5);
    }
}
