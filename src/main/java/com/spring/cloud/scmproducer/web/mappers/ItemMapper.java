package com.spring.cloud.scmproducer.web.mappers;

import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    @Named(value = "useMe")
    ItemDTO itemToItemDTO(Item item);
    ItemDTO itemToItemDTOwithInventory(Item item);
    Item itemDTOToItem(ItemDTO itemDTO);
}
