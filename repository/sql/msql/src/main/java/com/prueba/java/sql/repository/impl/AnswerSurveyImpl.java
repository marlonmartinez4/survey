package com.prueba.java.sql.repository.impl;

import com.prueba.java.model.AnswerSurvey;
import com.prueba.java.repository.AnswerSurveyRepository;
import com.prueba.java.sql.repository.jpa.AnswerSurveyDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class AnswerSurveyImpl implements AnswerSurveyRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnswerSurveyDataRepository answerSurveyDataRepository;

    @Override
    public Flux<AnswerSurvey> findAll() {
        return Flux.fromIterable(answerSurveyDataRepository.findAll())
                .map(value -> modelMapper.map(value, AnswerSurvey.class));
    }
}
