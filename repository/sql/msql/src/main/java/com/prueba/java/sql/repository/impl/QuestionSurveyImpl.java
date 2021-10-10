package com.prueba.java.sql.repository.impl;

import com.prueba.java.model.QuestionSurvey;
import com.prueba.java.repository.QuestionSurveyRepository;
import com.prueba.java.sql.repository.jpa.QuestionSurveyDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QuestionSurveyImpl implements QuestionSurveyRepository {

    @Autowired
    private QuestionSurveyDataRepository questionSurveyDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Flux<QuestionSurvey> findAllByQuestionModuleId(int id) {
        return Flux.fromIterable(questionSurveyDataRepository.findAllByQuestionModuleId(id))
                .map(value -> modelMapper.map(value, QuestionSurvey.class));
    }

    @Override
    public Mono<QuestionSurvey> findById(int id) {
        return Mono.justOrEmpty(questionSurveyDataRepository.findById(id))
                .map(value -> modelMapper.map(value, QuestionSurvey.class));
    }
}
