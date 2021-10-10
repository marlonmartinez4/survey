package com.prueba.java.sql.repository.impl;

import com.prueba.java.model.Customer;
import com.prueba.java.repository.CustomerRepository;
import com.prueba.java.sql.repository.entity.CustomerEntity;
import com.prueba.java.sql.repository.jpa.CustomerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerImpl implements CustomerRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerDataRepository customerDataRepository;


    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerDataRepository.findAll())
                .map(value -> modelMapper.map(value, Customer.class));
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return Mono.justOrEmpty(customer)
                .map(value -> modelMapper.map(value, CustomerEntity.class))
                .flatMap(customerEntity -> Mono.just(customerDataRepository.save(customerEntity)))
                .map(value -> modelMapper.map(value, Customer.class));
    }
}
