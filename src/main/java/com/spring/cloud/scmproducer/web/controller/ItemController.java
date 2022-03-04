package com.spring.cloud.scmproducer.web.controller;

import com.spring.cloud.scmproducer.services.ItemService;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import lombok.RequiredArgsConstructor;
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

    @GetMapping({"/{itemId}"})
    public ResponseEntity<ItemDTO> getItemById(@NotNull @PathVariable UUID itemId){
    return new ResponseEntity<>(itemService.getItemById(itemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewItem(@Valid @NotNull @RequestBody ItemDTO itemDTO){
    ItemDTO savedItemDTO = itemService.saveNewItem(itemDTO);
    HttpHeaders httpHeaders = new HttpHeaders();
    //TODO: Add URL to Location
    httpHeaders.add("Location","/api/v1/items/"+savedItemDTO.getId().toString());

    return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
    }


    @PutMapping({"/{itemId}"})
    public ResponseEntity updateItemById(ItemDTO itemDTO,@PathVariable UUID itemId){
        ItemDTO savedItemDTO = itemService.updateItemById(itemDTO,itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{itemId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemById(@PathVariable UUID itemId){
        itemService.deleteItemById(itemId);
    }
}
