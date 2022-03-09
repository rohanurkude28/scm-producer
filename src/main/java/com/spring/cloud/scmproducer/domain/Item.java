package com.spring.cloud.scmproducer.domain;

import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type= "uuid-char" )
    @GenericGenerator( name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36,columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp modifiedDate;

    private String itemName;
    private ItemTypeEnum itemType;

    private Long batchNo;
    private BigDecimal price;
    private Integer minOnHand;
    private Integer quantityToProduce;
}
