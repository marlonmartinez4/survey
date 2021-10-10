package com.prueba.java.repository;


import com.prueba.java.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerRepository {

    Flux<Customer>findAll();
}
