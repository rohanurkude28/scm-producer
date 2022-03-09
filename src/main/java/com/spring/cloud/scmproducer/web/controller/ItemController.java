package com.spring.cloud.scmproducer.web.controller;

import com.spring.cloud.scmproducer.services.ItemService;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.model.ItemPagedList;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@RequestMapping("/api/v1/items")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    @GetMapping({"/{itemId}"})
    public ResponseEntity<ItemDTO> getItemById(@NotNull @PathVariable UUID itemId, @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) showInventoryOnHand = false;
        return new ResponseEntity<>(itemService.getItemById(itemId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewItem(@Valid @NotNull @RequestBody ItemDTO itemDTO) {
        ItemDTO savedItemDTO = itemService.saveNewItem(itemDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO: Add URL to Location
        httpHeaders.add("Location", "/api/v1/items/" + savedItemDTO.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping({"/{itemId}"})
    public ResponseEntity updateItemById(ItemDTO itemDTO, @PathVariable UUID itemId) {
        ItemDTO savedItemDTO = itemService.updateItemById(itemDTO, itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{itemId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemById(@PathVariable UUID itemId) {
        itemService.deleteItemById(itemId);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<ItemPagedList> listItems(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "itemName", required = false) String itemName,
                                                   @RequestParam(value = "itemType", required = false) ItemTypeEnum itemType,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (pageNumber == null || pageNumber < 0) pageNumber = DEFAULT_PAGE_NUMBER;
        if (pageSize == null || pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
        if(showInventoryOnHand == null) showInventoryOnHand=false;

        ItemPagedList ItemList = itemService.listsItems(itemName, itemType, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(ItemList, HttpStatus.OK);
    }

}
