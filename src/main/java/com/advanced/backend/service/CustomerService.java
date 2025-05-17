package com.advanced.backend.service;

import com.advanced.backend.entity.Customer;
import com.advanced.backend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return repo.findById(id);
    }

    public Optional<Customer> update(Long id, Customer updated) {
        return repo.findById(id).map(customer -> {
            customer.setName(updated.getName());
            customer.setEmail(updated.getEmail());
            return repo.save(customer);
        });
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
