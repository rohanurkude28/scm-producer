package com.spring.cloud.scmproducer.web.controller;

import com.spring.cloud.scmproducer.web.model.CustomerDTO;
import com.spring.cloud.scmproducer.web.model.ItemDTO;
import com.spring.cloud.scmproducer.web.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID customerId){
    return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomerDTO = customerService.saveNewCustomer(customerDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO: Add URL to Location
        httpHeaders.add("Location","/api/v1/customers/"+savedCustomerDTO.getId().toString());

        return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
    }


    @PutMapping({"/{customerId}"})
    public ResponseEntity handleUpdate(CustomerDTO customerDTO,@PathVariable UUID customerId){
        CustomerDTO savedCustomerDTO = customerService.updateCustomer(customerDTO,customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID customerId){
        customerService.deleteCustomer(customerId);
    }
}
