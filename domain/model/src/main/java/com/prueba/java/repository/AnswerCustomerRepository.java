package com.prueba.java.repository;

import com.prueba.java.model.AnswerCustomer;
import reactor.core.publisher.Flux;

public interface AnswerCustomerRepository {

    Flux<AnswerCustomer> findAll();
}
