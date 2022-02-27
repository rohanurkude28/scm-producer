package com.spring.cloud.scmproducer.web.service;

import com.spring.cloud.scmproducer.web.model.CustomerDTO;
import com.spring.cloud.scmproducer.web.model.ItemDTO;

import java.util.UUID;

public interface CustomerService {
    CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO, UUID customerId);

    CustomerDTO deleteCustomer(UUID customerId);
}
