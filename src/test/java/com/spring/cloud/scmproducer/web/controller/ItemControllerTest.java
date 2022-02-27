package com.spring.cloud.scmproducer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
@ComponentScan(basePackages = "com.spring.cloud.scmproducer.web")
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String URL = "/api/v1/items/";
    private static final ItemDTO mockItemDTO = ItemDTO.builder().build();

    @Test
    void getItemById() throws Exception {
        mockMvc.perform(get(URL+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewItem() throws Exception {
        String itemDTOJson =  objectMapper.writeValueAsString(mockItemDTO);

        mockMvc.perform(post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemDTOJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateItemById() throws Exception {
        String itemDTOJson =  objectMapper.writeValueAsString(mockItemDTO);

        mockMvc.perform(put(URL+ UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(itemDTOJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteItemById() throws Exception {
        mockMvc.perform(delete(URL+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}