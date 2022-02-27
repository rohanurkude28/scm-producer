package com.spring.cloud.scmproducer.web.model;


import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ItemPagedList extends PageImpl<ItemDTO> {
    public ItemPagedList(List<ItemDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ItemPagedList(List<ItemDTO> content) {
        super(content);
    }
}
