package com.prueba.java.sql.repository.impl;

import com.prueba.java.model.AnswerCustomer;
import com.prueba.java.repository.AnswerCustomerRepository;
import com.prueba.java.sql.repository.entity.AnswerCustomerEntity;
import com.prueba.java.sql.repository.jpa.AnswerCustomerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AnswerCustomerImpl implements AnswerCustomerRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnswerCustomerDataRepository answerCustomerDataRepository;

    @Override
    public Mono<AnswerCustomer> save(AnswerCustomer answerCustomer) {
        return Mono.justOrEmpty(answerCustomer)
                .map(answer -> modelMapper.map(answer, AnswerCustomerEntity.class))
                .flatMap(answerCustomerEntity ->  Mono.just(answerCustomerDataRepository.save(answerCustomerEntity)))
                .map(answer -> modelMapper.map(answer, AnswerCustomer.class));
    }
}
