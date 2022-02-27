package com.spring.cloud.scmproducer.web.service;

import com.spring.cloud.scmproducer.web.model.CustomerDTO;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDTO getCustomerById(UUID itemId){
        return CustomerDTO.builder().id(UUID.randomUUID()).name("DMart").build();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, UUID customerId) {
        //TODO: Implement update Logic
        return null;
    }

    @Override
    public CustomerDTO deleteCustomer(UUID customerId) {
        //TODO: Implement delete Logic
        log.info("Deleted Customer: "+customerId);
        return null;
    }
}
