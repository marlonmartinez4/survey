package com.prueba.java.repository;


import com.prueba.java.model.AnswerSurvey;
import reactor.core.publisher.Flux;

public interface AnswerSurveyRepository {

    Flux<AnswerSurvey> findAll();
}
