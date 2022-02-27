package com.spring.cloud.scmproducer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data // Getters/Setters, RequiredArgs, toString, equals/hashcode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private UUID id;

    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

    private String itemName;
    private ItemTypeEnum itemType;

    private Long batchNo;
    private BigDecimal price;
    private Integer quantityOnHand;
}
