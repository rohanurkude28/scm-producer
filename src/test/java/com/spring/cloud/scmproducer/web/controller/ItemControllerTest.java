package com.spring.cloud.scmproducer.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.scmproducer.domain.Item;
import com.spring.cloud.scmproducer.services.ItemService;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.model.ItemTypeEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "http",uriHost = "localhost",uriPort = 8080)
@WebMvcTest(ItemController.class)
@ComponentScan(basePackages = "com.spring.cloud.scmproducer")
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    private static final String URL = "/api/v1/items/";
    private static final ItemDTO mockItemDTO = ItemDTO.builder().id(null).itemName("MockItem").itemType(ItemTypeEnum.BAKERY).batchNo(1L).price(new BigDecimal(1)).build();;

    @Test
    void getItemById() throws Exception {
        given(itemService.getItemById(any())).willReturn(getValidItemDto());

        mockMvc.perform(get("/api/v1/items/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    void saveNewItem() throws Exception {
//        String itemDTOJson =  objectMapper.writeValueAsString(mockItemDTO);
//
//        ConstrainedFields fields = new ConstrainedFields(ItemDTO.class);
//
//        mockMvc.perform(post(URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(itemDTOJson))
//                .andExpect(status().isCreated())
//                .andDo(document("v1/items",
//                        requestFields(
//                                fields.withPath("id").ignored(),
//                                fields.withPath("version").ignored(),
//                                fields.withPath("createdDate").ignored(),
//                                fields.withPath("modifiedDate").ignored(),
//                                fields.withPath("itemName").description("Name of the Item"),
//                                fields.withPath("itemType").description("Style of Item"),
//                                fields.withPath("batchNo").description("Item BatchNo").attributes(),
//                                fields.withPath("price").description("Item Price"),
//                                fields.withPath("quantityOnHand").ignored()
//                        )));
//    }
//
//    @Test
//    void updateItemById() throws Exception {
//        String itemDTOJson =  objectMapper.writeValueAsString(mockItemDTO);
//
//        mockMvc.perform(put(URL+ UUID.randomUUID())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(itemDTOJson))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void deleteItemById() throws Exception {
//        mockMvc.perform(delete(URL+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }

    ItemDTO getValidItemDto(){
        return ItemDTO.builder()
                .itemName("Maggi")
                .itemType(ItemTypeEnum.CANNED_GOODS)
                .quantityOnHand(500)
                .price(new BigDecimal("4.99"))
                .batchNo(Long.valueOf(1)).build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}