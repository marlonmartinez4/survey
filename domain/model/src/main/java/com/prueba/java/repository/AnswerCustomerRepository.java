package com.prueba.java.repository;

import com.prueba.java.model.AnswerCustomer;
import reactor.core.publisher.Mono;

public interface AnswerCustomerRepository {

    Mono<AnswerCustomer> save(AnswerCustomer answerCustomer);
}
