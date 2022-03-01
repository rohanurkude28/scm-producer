package com.spring.cloud.scmproducer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data // Getters/Setters, RequiredArgs, toString, equals/hashcode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @Null
    private UUID id;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime modifiedDate;

    @Null
    private Long version;

    @NotBlank
    private String itemName;

    @NotNull
    private ItemTypeEnum itemType;

    @Positive
    @NotNull
    private Long batchNo;

    @Positive
    @NotNull
    private BigDecimal price;


    private Integer quantityOnHand;
}
