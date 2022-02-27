package com.spring.cloud.scmproducer.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data // Getters/Setters, RequiredArgs, toString, equals/hashcode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private UUID id;
    private String name;
    private String type;
}
