package com.spring.cloud.scmproducer.services.inventory;

import com.spring.cloud.scmproducer.services.inventory.model.ItemInventoryDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "scm.producer", ignoreUnknownFields = false)
@Getter
@Setter
@Component
public class ItemInventoryServiceRestTemplateImpl implements ItemInventoryService{
    private final String INVENTORY_PATH = "/api/v1/item/{itemId}/inventory";
    private final RestTemplate restTemplate;
    private String itemInventoryServiceHost;

    public ItemInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID itemId) {

        log.debug("Calling Inventory Service");

        ResponseEntity<List<ItemInventoryDto>> responseEntity = restTemplate
                .exchange(itemInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ItemInventoryDto>>(){}, (Object) itemId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(ItemInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
