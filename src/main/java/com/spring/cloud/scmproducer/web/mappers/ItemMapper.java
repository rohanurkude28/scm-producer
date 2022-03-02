package com.spring.cloud.scmproducer.web.mappers;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ItemMapper {

    ItemDTO itemToItemDTO(Item item);
    Item itemDTOToItem(ItemDTO itemDTO);
}