package com.spring.cloud.scmproducer.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    private OffsetDateTime modifiedDate;

    @Null
    private Long version;

    @NotBlank
    @Size(min = 3,max = 100)
    private String itemName;

    @NotNull
    private ItemTypeEnum itemType;

    @Positive
    @NotNull
    private Long batchNo;

    @Positive
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;


    private Integer quantityOnHand;
}
