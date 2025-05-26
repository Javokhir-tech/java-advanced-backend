package com.advanced.backend.rest;


import com.advanced.backend.entity.Customer;
import com.advanced.backend.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return customerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer updated) {
        return customerService.getById(id)
                .map(customer -> {
                    customer.setName(updated.getName());
                    customer.setEmail(updated.getEmail());
                    return ResponseEntity.ok(customerService.create(customer));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (customerService.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

