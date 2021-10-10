package com.prueba.java.repository;


import com.prueba.java.model.QuestionModule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuestionModuleRepository  {

    Flux<QuestionModule> findAll();
    Mono<QuestionModule> findById(int id);
}
