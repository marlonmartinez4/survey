package com.prueba.java.sql.repository.impl;


import com.prueba.java.model.QuestionModule;
import com.prueba.java.repository.QuestionModuleRepository;
import com.prueba.java.sql.repository.jpa.QuestionModuleDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QuestionModuleImpl implements QuestionModuleRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionModuleDataRepository questionModuleDataRepository;


    @Override
    public Flux<QuestionModule> findAll() {
        return Flux.fromIterable(questionModuleDataRepository.findAll())
                .map(value -> modelMapper.map(value, QuestionModule.class));
    }

    @Override
    public Mono<QuestionModule> findById(int id) {
        return Mono.justOrEmpty(questionModuleDataRepository.findById(id))
                .map(value -> modelMapper.map(value, QuestionModule.class));
    }
}
